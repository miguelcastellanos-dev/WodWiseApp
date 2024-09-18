package com.migueldev.wodwiseapp.presentation.screen.calendar

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.composables.CalendarCardsList
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.theme.RotatingImageLoading

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
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            RotatingImageLoading(
                imageResource = R.drawable.ic_launcher_round,
                modifier = Modifier.align(Alignment.Center),
                size = Dimension.d128
            )
        }
    } else {
        CalendarCardsList(
            appState = appState,
            appActionState = appActionState,
            workoutsList = workoutsList
        )
    }
}
