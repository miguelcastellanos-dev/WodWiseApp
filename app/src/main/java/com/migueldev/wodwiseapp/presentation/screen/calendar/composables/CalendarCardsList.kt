package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.calendar.utils.groupWorkoutsByWeek
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalendarCardsList(
    appState: AppState,
    appActionState: AppActionState,
    workoutsList: List<WorkoutCardData>,
) {
    val groupWorkoutsByWeek = groupWorkoutsByWeek(workoutsList)

    LazyColumn(
        modifier = Modifier
            .padding(Dimension.d8)
            .fillMaxWidth()
    ) {
        groupWorkoutsByWeek.forEach { (week, workoutsListForWeek) ->
            stickyHeader {
                CalendarWeekHeaderCard(
                    week = week,
                    calendarViewModel = appState.calendarViewModel,
                    calendarState = appState.calendarState
                )
            }
            item {
                if (appState.calendarState.showWeekCardState[week] == true) {
                    CalendarCardsSortedByDay(
                        appState = appState,
                        appActionState = appActionState,
                        workoutsList = workoutsListForWeek
                    )
                }
            }
        }
    }
}
