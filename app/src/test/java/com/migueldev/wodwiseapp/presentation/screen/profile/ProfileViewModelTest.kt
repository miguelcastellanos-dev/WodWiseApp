package com.migueldev.wodwiseapp.presentation.screen.profile

import arrow.core.Either
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.repository.login.LoginRepository
import com.migueldev.wodwiseapp.domain.repository.weight.WeightRepository
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileTextResourceProvider
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProfileViewModelTest {

    private val toastWrapper = relaxedMockk<ToastWrapper>()
    private val profileTextResourceProvider = relaxedMockk<ProfileTextResourceProvider>()
    private val loginRepository = relaxedMockk<LoginRepository>()
    private val workoutRepository = relaxedMockk<WorkoutRepository>()
    private val weightRepository = relaxedMockk<WeightRepository>()
    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var viewModel: ProfileViewModel

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ProfileViewModel(
            toastWrapper = toastWrapper,
            profileTextResourceProvider = profileTextResourceProvider,
            loginRepository = loginRepository,
            workoutRepository = workoutRepository,
            weightRepository = weightRepository,
            ioDispatcher = testDispatcher
        )
    }

    @Test
    fun `GIVEN deleteUser WHEN deleteUser is called THEN it calls loginRepository deleteUser`() =
        runTest(testDispatcher) {
            coEvery { loginRepository.deleteUser() } returns Either.Right(Unit)

            viewModel.deleteUser()

            coVerify { loginRepository.deleteUser() }
        }

    @Test
    fun `GIVEN deleteAllWorkouts WHEN deleteAllWorkouts THEN it calls workoutRepository deleteWorkoutCollection`() =
        runTest(testDispatcher) {
            coEvery { workoutRepository.deleteWorkoutCollection() } just Runs

            viewModel.deleteAllWorkouts()

            coVerify { workoutRepository.deleteWorkoutCollection() }
        }

    @Test
    fun `GIVEN deleteAllWeights WHEN deleteAllWeights THEN it calls weightRepository deleteWeightsCollection`() =
        runTest(testDispatcher) {
            coEvery { weightRepository.deleteWeightsCollection() } just Runs

            viewModel.deleteAllWeights()

            coVerify { weightRepository.deleteWeightsCollection() }
        }
}
