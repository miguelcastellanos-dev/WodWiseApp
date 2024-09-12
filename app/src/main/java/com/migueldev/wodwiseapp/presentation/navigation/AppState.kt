package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.data.session.UserPreferences
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

@OptIn(ExperimentalMaterial3Api::class)
data class AppState(
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
    val loginState: LoginState,
    val signUpState: SignUpState,
    val scaffoldState: ScaffoldState,
    val workoutState: WorkoutState,
    val calendarState: CalendarState,
    val coachState: CoachState,
    val weightState: WeightsState,
    val weightDetailState: WeightDetailState,
    val settingState: SettingState,
    val profileState: ProfileState,
    val navController: NavHostController,
    val userPreferences: UserPreferences,
    val startDestination: String,
    val datePickerState: DatePickerState,
)
