package com.migueldev.wodwiseapp.presentation.screen.scaffold

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.BottomNavigation
import com.migueldev.wodwiseapp.presentation.screen.scaffold.composables.ScaffoldTopBar
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldParamsData
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@Composable
fun ScaffoldScreen(
    appState: AppState,
    scaffoldParamsData: ScaffoldParamsData,
) {
    appState.scaffoldViewModel.logger("ScaffoldScreen", "Recomposition")

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
                        scaffoldParamsData = scaffoldParamsData,
                        onLogout = scaffoldParamsData.onLogout
                    )
                },
                bottomBar = {
                    BottomNavigation(
                        navController = navController,
                        scaffoldState = appState.scaffoldState
                    )
                },
                content = { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Screens under construction")
                    }
                }
            )
        }
    )
}
