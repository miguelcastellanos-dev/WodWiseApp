package com.migueldev.wodwiseapp.presentation

import android.content.Context
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.CalendarViewModel
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachViewModel
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.profile.ProfileViewModel
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.ScaffoldViewModel
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import com.migueldev.wodwiseapp.presentation.screen.setting.SettingViewModel
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel
import com.migueldev.wodwiseapp.presentation.screen.weight.WeightViewModel
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.WeightDetailViewModel
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutViewModel
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStateManager @Inject constructor(
    private val userPreferences: UserPreferences,
    private val resourceProvider: ResourceProvider,
    private val toastWrapper: ToastWrapper,
) {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun initializeAppState(
        viewModelGroup: ViewModelGroup,
        stateGroup: StateGroup,
        navController: NavHostController,
        startDestination: Boolean,
    ): AppState {
        return AppState(
            loginViewModel = viewModelGroup.loginViewModel,
            signUpViewModel = viewModelGroup.signUpViewModel,
            scaffoldViewModel = viewModelGroup.scaffoldViewModel,
            workoutViewModel = viewModelGroup.workoutViewModel,
            calendarViewModel = viewModelGroup.calendarViewModel,
            coachViewModel = viewModelGroup.coachViewModel,
            weightViewModel = viewModelGroup.weightViewModel,
            weightDetailViewModel = viewModelGroup.weightDetailViewModel,
            settingViewModel = viewModelGroup.settingViewModel,
            profileViewModel = viewModelGroup.profileViewModel,
            scaffoldState = stateGroup.scaffoldState,
            loginState = stateGroup.loginState,
            signUpState = stateGroup.signUpState,
            workoutState = stateGroup.workoutState,
            calendarState = stateGroup.calendarState,
            coachState = stateGroup.coachState,
            weightState = stateGroup.weightState,
            weightDetailState = stateGroup.weightDetailState,
            settingState = stateGroup.settingState,
            profileState = stateGroup.profileState,
            navController = navController,
            userPreferences = userPreferences,
            startDestination = determineStartDestination(startDestination),
            datePickerState = stateGroup.datePickerState
        )
    }

    private fun determineStartDestination(isLoggedIn: Boolean): String {
        return if (isLoggedIn) Routes.ScaffoldScreen.route else Routes.LoginScreen.route
    }

    @Composable
    fun ObserveUserEmail(
        isEmailLoading: MutableState<Boolean>,
        userEmail: MutableState<String?>,
    ) {
        LaunchedEffect(Unit) {
            userPreferences.getEmail().collect { email ->
                userEmail.value = email
                isEmailLoading.value = false
            }
        }
    }

    fun handlePermissionResult(
        workoutViewModel: WorkoutViewModel,
        context: Context,
        isGranted: Boolean,
    ) {
        if (isGranted) {
            workoutViewModel.recordAudio(context)
        } else {
            toastWrapper.show(resourceProvider.getString(R.string.permission_denied))
        }
    }
}

data class ViewModelGroup(
    val loginViewModel: LoginViewModel,
    val signUpViewModel: SignUpViewModel,
    val scaffoldViewModel: ScaffoldViewModel,
    val workoutViewModel: WorkoutViewModel,
    val calendarViewModel: CalendarViewModel,
    val coachViewModel: CoachViewModel,
    val weightViewModel: WeightViewModel,
    val weightDetailViewModel: WeightDetailViewModel,
    val settingViewModel: SettingViewModel,
    val profileViewModel: ProfileViewModel,
)

@OptIn(ExperimentalMaterial3Api::class)
data class StateGroup(
    val loginState: LoginState,
    val signUpState: SignUpState,
    val scaffoldState: ScaffoldState,
    val workoutState: WorkoutState,
    val datePickerState: DatePickerState,
    val calendarState: CalendarState,
    val coachState: CoachState,
    val weightState: WeightsState,
    val weightDetailState: WeightDetailState,
    val settingState: SettingState,
    val profileState: ProfileState,
)
