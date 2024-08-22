package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.scaffold.ScaffoldScreenContent
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginScreen
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel
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

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.scaffoldScreen(
    appState: AppState,
    appButtonsState: AppButtonsState,
    datePickerState: DatePickerState,
) {
    composable(Routes.ScaffoldScreen.route) {
        ScaffoldScreenContent(
            appState = appState,
            appButtonsState = appButtonsState,
            datePickerState = datePickerState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.addWorkoutScreen(
    workoutViewModel: WorkoutViewModel,
    workoutState: WorkoutState,
    datePickerState: DatePickerState,
    appButtonsState: AppButtonsState,
) {
    composable(Routes.AddWorkoutScreen.route) {
        WorkoutScreenContent(
            workoutViewModel = workoutViewModel,
            workoutState = workoutState,
            datePickerState = datePickerState,
            appButtonsState = appButtonsState
        )
    }
}
