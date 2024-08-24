package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.runtime.Composable

@Composable
fun AppNavigation(
    appState: AppState,
) {
    val appButtonsState = createAppButtonsState(
        appState = appState
    )

    SetupNavHost(
        appState = appState,
        appButtonsState = appButtonsState
    )
}
