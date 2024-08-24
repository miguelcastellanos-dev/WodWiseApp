package com.migueldev.wodwiseapp.presentation.screen.calendar.utils

import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData

fun groupWorkoutsByDay(
    workouts: List<WorkoutCardData>,
): Map<String, List<WorkoutCardData>> {
    return workouts.groupBy { workout ->
        val date = workout.date
        date
    }
}
