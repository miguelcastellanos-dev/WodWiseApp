package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createBarbellMovementsExerciseData(
    resourceProvider: ResourceProvider,
): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_squat_clean_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_power_clean_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_hang_power_clean_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_squat_snatch_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_power_snatch_exercise),
            false
        ),

        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_hang_snatch_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_push_jerk_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_split_jerk_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_push_press_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_thruster_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.barbell_lunges_exercise),
            false
        )
    )
}
