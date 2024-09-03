package com.migueldev.wodwiseapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavHost(
    appState: AppState,
    appActionState: AppActionState,
    coachActionState: CoachActionState,
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
                appActionState = appActionState,
                coachActionState = coachActionState
            )
            weightDetailScreen(
                weightViewModel = weightViewModel,
                weightDetailState = weightDetailState,
                appActionState = appActionState
            )
        }
    }
}
