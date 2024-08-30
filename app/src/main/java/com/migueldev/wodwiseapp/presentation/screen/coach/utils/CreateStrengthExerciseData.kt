package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createStrengthExerciseData(resourceProvider: ResourceProvider): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_dead_lift_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_weighted_dip_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_bench_press_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_back_squat_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_front_squat_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_overhead_squat_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_shoulder_press_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.strength_weighted_pull_up_exercise),
            false
        )
    )
}
