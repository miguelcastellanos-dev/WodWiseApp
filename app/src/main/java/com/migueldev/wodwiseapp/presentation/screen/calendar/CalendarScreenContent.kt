package com.migueldev.wodwiseapp.presentation.screen.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.composables.CalendarCardsList

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CalendarScreenContent(
    appState: AppState,
    appActionState: AppActionState,
) {
    LaunchedEffect(Unit) {
        appState.calendarViewModel.getWorkoutsFromDatabase()
    }

    val workoutsList = appState.calendarViewModel.updateCalendar()

    if (appState.calendarState.isLoading) {
        CircularProgressIndicator()
    } else {
        CalendarCardsList(
            appState = appState,
            appActionState = appActionState,
            workoutsList = workoutsList
        )
    }
}
