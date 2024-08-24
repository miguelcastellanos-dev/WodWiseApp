package com.migueldev.wodwiseapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    appState: AppState,
) {
    val appButtonsState = createAppActionState(
        appState = appState
    )

    SetupNavHost(
        appState = appState,
        appActionState = appButtonsState
    )
}
