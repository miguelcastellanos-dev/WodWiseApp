package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@Composable
fun AppNavigation(
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
                appState = appState
            )
        }
    }
}
