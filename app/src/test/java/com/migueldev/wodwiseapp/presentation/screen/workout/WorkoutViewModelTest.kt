package com.migueldev.wodwiseapp.presentation.screen.workout

import arrow.core.Either
import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.domain.usecase.SaveWorkoutUseCase
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.workout.audio.AudioTranscriptionManager
import com.migueldev.wodwiseapp.presentation.screen.workout.data.ExerciseTypeItem
import com.migueldev.wodwiseapp.presentation.screen.workout.data.PositionSessionItem
import com.migueldev.wodwiseapp.presentation.screen.workout.data.SessionItem
import com.migueldev.wodwiseapp.presentation.screen.workout.data.TextResourceProvider
import io.mockk.coEvery
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WorkoutViewModelTest {
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val resourceProvider = relaxedMockk<ResourceProvider>()
    private val saveWorkoutUseCase = relaxedMockk<SaveWorkoutUseCase>()
    private val audioTranscriptionManager = relaxedMockk<AudioTranscriptionManager>()
    private val textResourceProvider = TextResourceProvider(resourceProvider)

    private lateinit var viewModel: WorkoutViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        viewModel = WorkoutViewModel(
            ioDispatcher = testDispatcher,
            saveWorkoutUseCase = saveWorkoutUseCase,
            textResourceProvider = textResourceProvider,
            audioTranscriptionManager = audioTranscriptionManager
        )
    }

    @Test
    fun `GIVEN instructionsText WHEN updateInstructionsText is called THEN instructionsState updated`(): Unit =
        runTest {
            val instructionsText = "Run"

            viewModel.updateInstructionsText(instructionsText)

            viewModel.state.value.instructionsStateText shouldBeEqualTo instructionsText
        }

    @Test
    fun `GIVEN session WHEN updateSelectedSession is called THEN selectedSession updated`(): Unit =
        runTest {
            val session = SessionItem(resourceProvider.getString(R.string.session_1))

            viewModel.updateSelectedSession(session)

            session shouldBeEqualTo viewModel.state.value.sessionStateText
        }

    @Test
    fun `GIVEN position WHEN updateSelectedPosition is called THEN selectedPosition updated`(): Unit =
        runTest {
            val position = PositionSessionItem(resourceProvider.getString(R.string.position_b))

            viewModel.updateSelectedPosition(position)

            position shouldBeEqualTo viewModel.state.value.positionStateText
        }

    @Test
    fun `GIVEN exerciseType WHEN updateSelectedExerciseType is called THEN selectedExerciseType updated`(): Unit =
        runTest {
            val exerciseType = ExerciseTypeItem(resourceProvider.getString(R.string.exercise_wod))

            viewModel.updateSelectedExerciseType(exerciseType)

            exerciseType shouldBeEqualTo viewModel.state.value.exerciseTypeStateText
        }

    @Test
    fun `GIVEN valid input WHEN saveWorkout is called THEN returns right with workoutDto`() =
        runTest(testDispatcher) {
            val instructions = "Instructions"
            val session = "Session 1"
            val position = "A"
            val exerciseType = "Wod"
            val dateMillis: Long? = null
            val notesInitialText = ""
            val workoutDto = WorkoutDto(
                workoutId = "123",
                date = Timestamp.now(),
                session = session,
                positionSession = position,
                exerciseType = exerciseType,
                instructions = instructions,
                checkboxState = false,
                notes = "Some notes"
            )
            val expectedResult = Either.Right(workoutDto)
            coEvery {
                saveWorkoutUseCase(
                    workoutState = any(),
                    instructions = instructions,
                    session = session,
                    position = position,
                    exerciseType = exerciseType,
                    dateMillis = dateMillis,
                    notesInitialText = notesInitialText
                )
            } returns expectedResult
            val result = viewModel.saveWorkout(instructions, session, position, exerciseType, dateMillis)

            result.shouldBeInstanceOf<Either.Right<WorkoutDto>>()
            val rightResult = result as Either.Right
            rightResult.value shouldBeEqualTo workoutDto

            coVerifyOnce {
                saveWorkoutUseCase(
                    workoutState = any(),
                    instructions = instructions,
                    session = session,
                    position = position,
                    exerciseType = exerciseType,
                    dateMillis = dateMillis,
                    notesInitialText = notesInitialText
                )
            }
        }
}
