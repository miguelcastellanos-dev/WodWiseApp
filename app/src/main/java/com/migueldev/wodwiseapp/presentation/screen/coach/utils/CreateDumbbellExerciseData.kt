package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createDumbbellExerciseData(resourceProvider: ResourceProvider): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_snatch_dumbbell_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_clean_and_jerk_dumbbell_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_thruster_dumbbell_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_bench_press_dumbbell_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_deadlift_dumbbell_exercise),
            false
        ),

        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_farmer_carry_dumbbell_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_push_press_dumbbell_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.dumbbell_lunges_dumbbell_exercise),
            false
        )
    )
}
