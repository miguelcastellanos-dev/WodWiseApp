package com.migueldev.wodwiseapp.presentation.screen.coach

import androidx.compose.runtime.mutableStateOf
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.usecase.CreateTrainingInfoUseCase
import com.migueldev.wodwiseapp.domain.usecase.HandleResultIAUseCase
import com.migueldev.wodwiseapp.domain.usecase.RequestChatCompletionIAUseCase
import com.migueldev.wodwiseapp.domain.usecase.SaveWorkoutUseCase
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachTextResourceProvider
import io.mockk.every
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldContain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CoachViewModelTest {

    private val coachTextResourceProvider = relaxedMockk<CoachTextResourceProvider>()
    private val createTrainingInfoUseCase = relaxedMockk<CreateTrainingInfoUseCase>()
    private val handleResultIAUseCase = relaxedMockk<HandleResultIAUseCase>()
    private val toastWrapper = relaxedMockk<ToastWrapper>()
    private val requestChatCompletionIAUseCase = relaxedMockk<RequestChatCompletionIAUseCase>()
    private val saveWorkoutUseCase = relaxedMockk<SaveWorkoutUseCase>()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: CoachViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)

        every { coachTextResourceProvider.coachInitializeTextResources() } returns CoachState()

        viewModel = CoachViewModel(
            createTrainingInfoUseCase = createTrainingInfoUseCase,
            toastWrapper = toastWrapper,
            coachTextResourceProvider = coachTextResourceProvider,
            saveWorkoutUseCase = saveWorkoutUseCase,
            handleResultIAUseCase = handleResultIAUseCase,
            requestChatCompletionIAUseCase = requestChatCompletionIAUseCase,
            ioDispatcher = testDispatcher,
            mainDispatcher = testDispatcher
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN state WHEN handleDialogState is called with false THEN dialog is hidden`() =
        runTest(testDispatcher) {
            val response = "response"
            every { coachTextResourceProvider.coachInitializeTextResources() } returns CoachState(
                answerOpenAi = response,
                showResponseDialog = mutableStateOf(true)
            )

            viewModel.handleDialogState(false)

            viewModel.showDialog() shouldBe false
        }

    @Test
    fun `GIVEN selected time WHEN updateSelectedTime is called THEN selectedTime updated`(): Unit =
        runTest(testDispatcher) {
            val selectedTime = 45

            viewModel.updateSelectedTime(selectedTime)

            viewModel.state.value.selectedTime shouldBeEqualTo selectedTime
        }

    @Test
    fun `GIVEN selected format WHEN updateSelectedFormat is called THEN selectedFormat updated`(): Unit =
        runTest(testDispatcher) {
            val selectedFormat = "For time"

            viewModel.updateSelectedFormat(selectedFormat)

            viewModel.state.value.selectedFormat shouldBeEqualTo selectedFormat
        }

    @Test
    fun `GIVEN selected exercise WHEN onExerciseSelected is called THEN selectedExercises updated`(): Unit =
        runTest(testDispatcher) {
            val exerciseName = "Pull ups"
            val isSelected = true

            viewModel.onExerciseSelected(exerciseName, isSelected)

            viewModel.state.value.selectedExerciseLists shouldContain exerciseName
        }

    @Test
    fun `GIVEN initial state WHEN foldOrUnfoldExerciseTab is called THEN state is updated accordingly`() =
        runTest(testDispatcher) {
            val date = "2024-08-29"
            every { coachTextResourceProvider.coachInitializeTextResources() } returns CoachState(
                showExerciseCardState = mutableMapOf(date to false)
            )

            viewModel.foldOrUnfoldExerciseTab(date)

            viewModel.state.value.showExerciseCardState[date] shouldBe true
        }
}
