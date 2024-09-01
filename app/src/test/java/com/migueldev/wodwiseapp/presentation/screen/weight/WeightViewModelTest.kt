package com.migueldev.wodwiseapp.presentation.screen.weight

import arrow.core.Either
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.domain.repository.weight.WeightRepository
import com.migueldev.wodwiseapp.domain.usecase.GenerateWorkoutIdUseCase
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightTextResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.weight.data.createWeightDto
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockkStatic
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeightViewModelTest {

    private val weightRepository = relaxedMockk<WeightRepository>()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val resourceProvider = relaxedMockk<ResourceProvider>()
    private val generateWorkoutIdUseCase = relaxedMockk<GenerateWorkoutIdUseCase>()
    private val weightTextResourceProvider = WeightTextResourceProvider(resourceProvider)

    private lateinit var viewModel: WeightViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = spyk(
            WeightViewModel(
                weightRepository = weightRepository,
                generateWorkoutIdUseCase = generateWorkoutIdUseCase,
                weightTextResourceProvider = weightTextResourceProvider,
                ioDispatcher = testDispatcher
            )
        )
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `GIVEN initial state WHEN getWeightFromDatabase is called THEN updates exercisesWeightList`() =
        runTest(testDispatcher) {
            val mockList = listOf(
                WeightData(weightId = "20240102", weightName = "Squat", weightRepetitionMaximum = 100.0),
                WeightData(weightId = "20240101", weightName = "Bench Press", weightRepetitionMaximum = 80.0)
            )
            coEvery { weightRepository.getWeights() } returns flowOf(mockList)

            viewModel.getWeightsFromDatabase()
            advanceUntilIdle()

            val currentState = viewModel.state.value
            val sortedList = mockList.sortedByDescending { it.weightId }
            sortedList shouldBeEqualTo currentState.exercisesWeightList
        }

    @Test
    fun `GIVEN valid input WHEN saveWeight is called THEN returns right with weightDto`() =
        runTest(testDispatcher) {
            val weightId = "20240101"
            val nameExercise = "Deadlift"
            val rm = 150.0
            val weightDto = WeightDto(weightId, nameExercise, rm)
            mockkStatic(::createWeightDto)
            coEvery { generateWorkoutIdUseCase() } returns weightId
            coEvery { weightRepository.addWeightToFirestore(weightDto) } returns Unit
            coEvery { createWeightDto(weightId, nameExercise, rm) } returns weightDto

            val result = viewModel.saveWeight(nameExercise, rm)

            (result is Either.Right) shouldBeEqualTo true
            val rightResult = result as Either.Right
            weightDto shouldBeEqualTo rightResult.value
            coVerify { generateWorkoutIdUseCase() }
            coVerify { weightRepository.addWeightToFirestore(weightDto) }
        }

    @Test
    fun `GIVEN weightId WHEN removeWeight is called THEN removes weight and updates list`() =
        runTest(testDispatcher) {
            val weightId = "20240101"
            val nameExercise = "Deadlift"
            val rm = 150.0
            val mockList = listOf(
                WeightData(weightId = weightId, weightName = nameExercise, weightRepetitionMaximum = rm)
            )
            coEvery { weightRepository.removeWeight(weightId) } returns Unit
            coEvery { weightRepository.getWeights() } returns flowOf(mockList.filter { it.weightId != weightId })

            viewModel.removeWeight(weightId)
            advanceUntilIdle()

            val currentState = viewModel.state.value
            (currentState.exercisesWeightList.none { it.weightId == weightId }) shouldBeEqualTo true
        }
}
