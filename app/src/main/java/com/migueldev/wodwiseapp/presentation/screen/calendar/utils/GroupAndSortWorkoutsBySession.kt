package com.migueldev.wodwiseapp.presentation.screen.calendar.utils

import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData

fun groupAndSortWorkoutsBySession(
    workouts: List<WorkoutCardData>,
): Map<String, List<WorkoutCardData>> {
    return workouts
        .groupBy { it.session }
        .mapValues { entry ->
            entry.value
                .sortedWith(
                    compareBy<WorkoutCardData> {
                        it.positionSession
                    }
                        .thenBy { it.exerciseType }
                )
        }
}
