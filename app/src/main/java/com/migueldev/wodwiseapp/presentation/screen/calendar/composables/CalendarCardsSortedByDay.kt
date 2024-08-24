package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.calendar.utils.groupWorkoutsByDay

@Composable
fun CalendarCardsSortedByDay(
    appState: AppState,
    appActionState: AppActionState,
    workoutsList: List<WorkoutCardData>,
) {
    val groupWorkoutsByDay = groupWorkoutsByDay(workoutsList)
    groupWorkoutsByDay.forEach { (date, workoutsListForDay) ->
        val showDayCardState = appState.calendarState.showDayCardState[date] ?: false
        CalendarDayCards(
            appState = appState,
            appActionState = appActionState,
            date = date,
            workoutsList = workoutsListForDay,
            showDayCardState = showDayCardState
        )
    }
}
