package com.migueldev.wodwiseapp.presentation.screen.calendar

import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.core.verifyOnce
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.model.CreateRouteCalendarDetailParams
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import io.mockk.every
import io.mockk.spyk
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import java.util.Locale
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CalendarViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private val workoutRepository = relaxedMockk<WorkoutRepository>()
    private val resourceProvider = relaxedMockk<ResourceProvider>()
    private val toastWrapper = relaxedMockk<ToastWrapper>()
    private lateinit var navController: NavHostController

    private lateinit var viewModel: CalendarViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = spyk(
            CalendarViewModel(
                workoutRepository = workoutRepository,
                resourceProvider = resourceProvider,
                toastWrapper = toastWrapper,
                ioDispatcher = testDispatcher
            )
        )
        navController = relaxedMockk()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `WHEN getWorkoutsFromDatabase is called THEN state is updated with workouts and checkboxState`() =
        runTest(testDispatcher) {
            val workoutList = listOf(
                WorkoutCardData(
                    workoutId = "2",
                    date = "monday 12 january",
                    session = "1",
                    positionSession = "C",
                    exerciseType = "Cardio",
                    instructions = "Run for 30 minutes",
                    checkboxState = true,
                    notes = ""
                )
            )
            every { workoutRepository.getWorkouts() } returns flowOf(workoutList)

            viewModel.getWorkoutsFromDatabase()

            val currentState = viewModel.state.first()
            currentState.workoutsList shouldBeEqualTo workoutList
            currentState.checkboxState[workoutList.first().workoutId] shouldBeEqualTo true
            currentState.isLoading shouldBeEqualTo false
        }

    @Test
    fun `WHEN initializeTextResources is called THEN state is updated with correct text resources`() =
        runTest {
            val prefixTitleWeek = "Week"
            val instructionTextTitleInCardView = "Instructions"
            val notesTextTitleInCardView = "Notes"
            every {
                resourceProvider.getString(
                    R.string.prefix_title_week
                )
            } returns prefixTitleWeek
            every {
                resourceProvider.getString(
                    R.string.instruction_text_title_in_card_view
                )
            } returns instructionTextTitleInCardView
            every {
                resourceProvider.getString(
                    R.string.notes_text_title_in_card_view
                )
            } returns notesTextTitleInCardView

            viewModel::class.java.getDeclaredMethod("initializeTextResources").apply {
                isAccessible = true
                invoke(viewModel)
            }

            val currentState = viewModel.state.first()
            currentState.prefixTitleWeekText shouldBeEqualTo prefixTitleWeek
            currentState.instructionTextTitleInCardView shouldBeEqualTo instructionTextTitleInCardView
            currentState.notesTextTitleInCardView shouldBeEqualTo notesTextTitleInCardView
        }

    @Test
    fun `GIVEN workoutId and newState WHEN updateCheckboxState is called THEN updated Checkbox State`() =
        runTest(testDispatcher) {
            val workoutId = "123L"
            val newState = true
            viewModel.updateCalendar()

            viewModel.updateCheckboxState(workoutId, newState)

            val currentState = viewModel.state.first()
            val updatedState = currentState.checkboxState[workoutId]
            updatedState shouldBeEqualTo newState
            coVerifyOnce { viewModel.updateCheckboxState(workoutId, newState) }
        }

    @Test
    fun `GIVEN calendar has workouts WHEN updateCalendar is called THEN return WorkoutCardData list`() =
        runTest(testDispatcher) {
            val workoutList = listOf(
                WorkoutCardData(
                    workoutId = "2",
                    date = "monday 12 january",
                    session = "1",
                    positionSession = "C",
                    exerciseType = "Cardio",
                    instructions = "Run for 30 minutes",
                    checkboxState = true,
                    notes = ""
                )
            )
            every { workoutRepository.getWorkouts() } returns flowOf(workoutList)

            viewModel.initializeState(CalendarState(workoutsList = workoutList))

            val result = viewModel.updateCalendar()
            result shouldBeEqualTo workoutList
        }

    @Test
    fun `GIVEN a date WHEN foldOrUnfoldDayTab is called THEN Unfold that date and fold others`() =
        runTest(testDispatcher) {
            val date = "2024-12-12"
            val otherDate = "2024-12-13"
            val initialState = CalendarState(
                showDayCardState = mutableMapOf(
                    date to false,
                    otherDate to true
                )
            )
            viewModel.initializeState(initialState)
            viewModel.updateCalendar()

            viewModel.foldOrUnfoldDayTab(date)

            val newState = viewModel.state.first()
            newState.showDayCardState[date] shouldBeEqualTo true
            newState.showDayCardState[otherDate] shouldBeEqualTo false
        }

    @Test
    fun `GIVEN a date WHEN foldOrUnfoldWeekTab is called THEN Unfold that date and fold others`() =
        runTest(testDispatcher) {
            val date = "2024-12-12"
            val otherDate = "2024-12-13"
            val initialState = CalendarState(
                showWeekCardState = mutableMapOf(
                    date to false,
                    otherDate to true
                )
            )
            viewModel.initializeState(initialState)
            viewModel.updateCalendar()

            viewModel.foldOrUnfoldWeekTab(date)

            val newState = viewModel.state.first()
            newState.showWeekCardState[date] shouldBeEqualTo true
            newState.showWeekCardState[otherDate] shouldBeEqualTo false
        }

    @Test
    fun `WHEN deleteWorkoutItem is called THEN repository removes workout item`() =
        runTest(testDispatcher) {
            val workoutId = "1"

            viewModel.deleteWorkoutItem(workoutId)

            coVerifyOnce { workoutRepository.removeWorkout(workoutId) }
        }

    @Test
    fun `GIVEN workoutId and newNotes WHEN updateNotesText is called THEN notesText is updated and toast is shown`() =
        runTest(testDispatcher) {
            val workoutId = "123L"
            val newNotes = "New workout notes"
            val initialState = CalendarState(notesText = mutableMapOf(workoutId to "Old notes"))

            viewModel.initializeState(initialState)
            viewModel.updateNotesText(workoutId, newNotes)

            val currentState = viewModel.state.first()
            currentState.notesText[workoutId] shouldBeEqualTo newNotes
            coVerifyOnce { workoutRepository.updateNotesState(workoutId, newNotes) }
        }

    @Test
    fun `GIVEN workoutId and newInstructions WHEN updateInstructionsText is called THEN instructionsText is updated`() =
        runTest(testDispatcher) {
            val workoutId = "123L"
            val newInstructions = "New workout instructions"
            val initialState = CalendarState(instructionsText = mutableMapOf(workoutId to "Old instructions"))

            viewModel.initializeState(initialState)
            viewModel.updateInstructionsText(workoutId, newInstructions)

            val currentState = viewModel.state.first()
            currentState.instructionsText[workoutId] shouldBeEqualTo newInstructions
            coVerifyOnce { workoutRepository.updateInstructionsState(workoutId, newInstructions) }
        }

    @Test
    fun `GIVEN workout WHEN sendWorkoutToDetail is called THEN navigate with correct parameters`() {
        val workout = WorkoutCardData(
            workoutId = "2",
            date = "monday 12 january",
            session = "1",
            positionSession = "C",
            exerciseType = "Cardio",
            instructions = "Run for 30 minutes",
            checkboxState = true,
            notes = "Workout notes"
        )
        val encodedInstructions = URLEncoder.encode(workout.instructions, StandardCharsets.UTF_8.toString())
        val encodedNotes = URLEncoder.encode(workout.notes, StandardCharsets.UTF_8.toString())
        val params = CreateRouteCalendarDetailParams(
            positionSession = workout.positionSession.uppercase(Locale.ROOT),
            exercise = workout.exerciseType.uppercase(Locale.ROOT),
            instructions = encodedInstructions,
            workoutId = workout.workoutId,
            date = workout.date,
            session = workout.session,
            notes = encodedNotes
        )

        val sendWorkoutToDetail = viewModel.sendWorkoutToDetail(navController)
        sendWorkoutToDetail(workout)

        verifyOnce {
            navController.navigate(Routes.CalendarDetailScreen.createRoute(params))
        }
    }
}
