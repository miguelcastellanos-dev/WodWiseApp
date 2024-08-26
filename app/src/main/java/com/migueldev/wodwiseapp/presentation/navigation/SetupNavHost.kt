package com.migueldev.wodwiseapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavHost(
    appActionState: AppActionState,
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
                appActionState = appActionState
            )
        }
    }
}
