package com.migueldev.wodwiseapp.presentation.screen.calendar

import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import io.mockk.every
import io.mockk.spyk
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
    private lateinit var navController: NavHostController

    private lateinit var viewModel: CalendarViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = spyk(
            CalendarViewModel(
                workoutRepository = workoutRepository,
                resourceProvider = resourceProvider,
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
}
