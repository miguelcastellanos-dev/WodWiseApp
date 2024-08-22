package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavHost(
    appButtonsState: AppButtonsState,
    appState: AppState,
) {
    with(appState) {
        NavHost(
            navController = navController,
            startDestination = startDestination
        ) {
            loginScreen(
                loginState = loginState,
                loginViewModel = loginViewModel,
                navController = navController
            )
            signUpScreen(
                loginState = loginState,
                signUpState = signUpState,
                signUpViewModel = signUpViewModel,
                navController = navController
            )
            scaffoldScreen(
                appState = appState,
                appButtonsState = appButtonsState,
                datePickerState = datePickerState
            )
        }
    }
}
