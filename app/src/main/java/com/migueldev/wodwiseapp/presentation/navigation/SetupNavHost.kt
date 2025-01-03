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
                appActionState = appActionState,
                appState = appState
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
                appState = appState,
                appActionState = appActionState
            )
            calendarDetailScreen(
                appState = appState,
                appActionState = appActionState
            )
            settingScreen(
                settingState = settingState,
                appActionState = appActionState
            )
            profileScreen(
                settingState = settingState,
                profileState = profileState,
                appActionState = appActionState
            )
        }
    }
}
