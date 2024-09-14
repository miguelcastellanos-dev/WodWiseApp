package com.migueldev.wodwiseapp.presentation.screen.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.repository.login.LoginRepository
import com.migueldev.wodwiseapp.domain.repository.weight.WeightRepository
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileTextResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val toastWrapper: ToastWrapper,
    private val profileTextResourceProvider: ProfileTextResourceProvider,
    private val loginRepository: LoginRepository,
    private val workoutRepository: WorkoutRepository,
    private val weightRepository: WeightRepository,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _profileState = MutableStateFlow(ProfileState())
    val state: StateFlow<ProfileState> = _profileState

    init {
        initializeTextResources()
    }

    private fun initializeTextResources() {
        _profileState.value = profileTextResourceProvider.profileInitializeTextResources()
    }

    fun deleteUser() {
        viewModelScope.launch(ioDispatcher) {
            loginRepository.deleteUser()
        }
        toastWrapper.show(_profileState.value.deleteUserToastWrapperTest)
    }

    fun deleteAllWorkouts() {
        viewModelScope.launch(ioDispatcher) {
            workoutRepository.deleteWorkoutCollection()
        }
        toastWrapper.show(_profileState.value.deleteWorkoutsToastWrapperTest)
    }

    fun deleteAllWeights() {
        viewModelScope.launch(ioDispatcher) {
            weightRepository.deleteWeightsCollection()
        }
        toastWrapper.show(_profileState.value.deleteWeightsToastWrapperTest)
    }
}
