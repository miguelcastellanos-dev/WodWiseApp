package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.navigation.addWorkoutScreen
import com.migueldev.wodwiseapp.presentation.navigation.calendarScreen
import com.migueldev.wodwiseapp.presentation.navigation.coachScreen
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupScaffoldNavHost(
    appState: AppState,
    appActionState: AppActionState,
    coachActionState: CoachActionState,
    navController: NavHostController,
) {
    with(appState) {
        NavHost(
            navController = navController,
            startDestination = Routes.CalendarScreen.route
        ) {
            calendarScreen(
                appState = appState,
                appActionState = appActionState
            )
            addWorkoutScreen(
                workoutViewModel = workoutViewModel,
                workoutState = workoutState,
                datePickerState = datePickerState,
                appActionState = appActionState
            )
            coachScreen(
                coachState = coachState,
                intelligenceViewModel = coachViewModel,
                coachActionState = coachActionState
            )
        }
    }
}
