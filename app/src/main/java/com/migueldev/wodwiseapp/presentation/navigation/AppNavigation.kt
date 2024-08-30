package com.migueldev.wodwiseapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.coach.data.createCoachActionState

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    appState: AppState,
) {
    val appActionState = createAppActionState(
        appState = appState
    )
    val coachActionState = createCoachActionState(
        coachViewModel = appState.coachViewModel,
        coachState = appState.coachState
    )

    SetupNavHost(
        appState = appState,
        appActionState = appActionState,
        coachActionState = coachActionState
    )
}
