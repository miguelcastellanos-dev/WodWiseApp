package com.migueldev.wodwiseapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.calendar.CalendarScreenContent
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachScreenContent
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachViewModel
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.ScaffoldScreenContent
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginScreen
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel
import com.migueldev.wodwiseapp.presentation.screen.weight.WeightScreenContent
import com.migueldev.wodwiseapp.presentation.screen.weight.WeightViewModel
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutScreenContent
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutViewModel
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

fun NavGraphBuilder.loginScreen(
    loginState: LoginState,
    loginViewModel: LoginViewModel,
    navController: NavHostController,
) {
    composable(Routes.LoginScreen.route) {
        LoginScreen(
            loginState = loginState,
            loginViewModel = loginViewModel,
            navController = navController
        )
    }
}

fun NavGraphBuilder.signUpScreen(
    loginState: LoginState,
    signUpState: SignUpState,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
) {
    composable(Routes.SignUpScreen.route) {
        SignUpScreen(
            loginState = loginState,
            signUpState = signUpState,
            signUpViewModel = signUpViewModel,
            navController = navController
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.scaffoldScreen(
    appState: AppState,
    appActionState: AppActionState,
    coachActionState: CoachActionState,
) {
    composable(Routes.ScaffoldScreen.route) {
        ScaffoldScreenContent(
            appState = appState,
            appActionState = appActionState,
            coachActionState = coachActionState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.addWorkoutScreen(
    workoutViewModel: WorkoutViewModel,
    workoutState: WorkoutState,
    datePickerState: DatePickerState,
    appActionState: AppActionState,
) {
    composable(Routes.WorkoutScreen.route) {
        WorkoutScreenContent(
            workoutViewModel = workoutViewModel,
            workoutState = workoutState,
            datePickerState = datePickerState,
            appActionState = appActionState
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.calendarScreen(
    appState: AppState,
    appActionState: AppActionState,
) {
    composable(Routes.CalendarScreen.route) {
        CalendarScreenContent(
            appState = appState,
            appActionState = appActionState
        )
    }
}

fun NavGraphBuilder.coachScreen(
    coachState: CoachState,
    intelligenceViewModel: CoachViewModel,
    coachActionState: CoachActionState,
) {
    composable(Routes.CoachScreen.route) {
        CoachScreenContent(
            coachState = coachState,
            coachViewModel = intelligenceViewModel,
            coachActionState = coachActionState
        )
    }
}

fun NavGraphBuilder.weightScreen(
    weightViewModel: WeightViewModel,
) {
    composable(Routes.WeightScreen.route) {
        WeightScreenContent(
            weightViewModel = weightViewModel
        )
    }
}
