package com.migueldev.wodwiseapp.presentation.screen.scaffold

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.migueldev.wodwiseapp.presentation.navigation.AppButtonsState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.ScaffoldBottomNavigationBar
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.ScaffoldTopBar
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.SetupScaffoldNavHost
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldScreenContent(
    appState: AppState,
    appButtonsState: AppButtonsState,
    datePickerState: DatePickerState,
) {
    appState.scaffoldViewModel.logger("ScaffoldScreenContent", "Recomposition")

    val scaffoldState by appState.scaffoldViewModel.state.collectAsState()
    val context = LocalContext.current
    val navController = rememberNavController()

    BackHandler {
        (context as? ComponentActivity)?.moveTaskToBack(true)
    }

    WodWiseAppTheme(
        darkTheme = scaffoldState.mode.themeMode == ThemeSwitcher.DARK,
        content = {
            Scaffold(
                topBar = {
                    ScaffoldTopBar(
                        scaffoldState = scaffoldState,
                        appButtonsState = appButtonsState
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
                            appButtonsState = appButtonsState,
                            datePickerState = datePickerState
                        )
                    }
                }
            )
        }
    )
}
