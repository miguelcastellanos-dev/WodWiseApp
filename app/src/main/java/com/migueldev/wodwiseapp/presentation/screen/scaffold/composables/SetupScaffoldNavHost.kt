package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.navigation.AppButtonsState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.navigation.addWorkoutScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupScaffoldNavHost(
    navController: NavHostController,
    appState: AppState,
    appButtonsState: AppButtonsState,
    datePickerState: DatePickerState,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.AddWorkoutScreen.route
    ) {
        addWorkoutScreen(
            workoutViewModel = appState.workoutViewModel,
            workoutState = appState.workoutState,
            datePickerState = datePickerState,
            appButtonsState = appButtonsState
        )
    }
}
