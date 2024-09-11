package com.migueldev.wodwiseapp.presentation.screen.scaffold

import android.os.Build
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.ScaffoldBottomNavigationBar
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.ScaffoldTopBar
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.SetupScaffoldNavHost
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ScaffoldScreenContent(
    appState: AppState,
    appActionState: AppActionState,
    coachActionState: CoachActionState,
) {
    appState.scaffoldViewModel.logger("ScaffoldScreenContent", "Recomposition")

    val scaffoldState by appState.scaffoldViewModel.state.collectAsState()
    val context = LocalContext.current
    val navController = rememberNavController()

    BackHandler {
        (context as? ComponentActivity)?.moveTaskToBack(true)
    }

    WodWiseAppTheme(
        darkTheme = appState.settingState.mode.themeMode == ThemeSwitcher.DARK,
        content = {
            Scaffold(
                topBar = {
                    ScaffoldTopBar(
                        navController = appState.navController,
                        scaffoldState = scaffoldState,
                        settingState = appState.settingState,
                        appActionState = appActionState
                    )
                },
                bottomBar = {
                    ScaffoldBottomNavigationBar(
                        navController = navController,
                        scaffoldState = appState.scaffoldState
                    )
                },
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        SetupScaffoldNavHost(
                            navController = navController,
                            appState = appState,
                            appActionState = appActionState,
                            coachActionState = coachActionState
                        )
                    }
                }
            )
        }
    )
}
