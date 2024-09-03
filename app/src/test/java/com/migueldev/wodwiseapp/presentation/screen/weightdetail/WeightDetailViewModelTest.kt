package com.migueldev.wodwiseapp.presentation.screen.weightdetail

import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.repository.weight.WeightRepository
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailTextResourceProvider
import io.mockk.spyk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeightDetailViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val resourceProvider = relaxedMockk<ResourceProvider>()
    private val textResourceProvider = WeightDetailTextResourceProvider(resourceProvider)
    private val weightRepository = relaxedMockk<WeightRepository>()

    private lateinit var viewModel: WeightDetailViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = spyk(
            WeightDetailViewModel(
                weightRepository = weightRepository,
                weightDetailTextResourceProvider = textResourceProvider,
                ioDispatcher = testDispatcher
            )
        )
    }

    @Test
    fun `GIVEN weightId and newRm WHEN updateRmInCoroutine is called THEN updateWeightRm is called`() =
        runTest(testDispatcher) {
            val weightId = "20240101"
            val newRm = 120.0

            viewModel.updateRmInCoroutine(weightId, newRm)
            advanceUntilIdle()

            coVerifyOnce { weightRepository.updateWeightRm(weightId, newRm) }
        }
}
