package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createKettlebellExerciseData(resourceProvider: ResourceProvider): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_swing_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_snatch_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_overhead_carry_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_farmer_carry_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_clean_and_press_exercise),
            false
        ),

        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_push_press_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_lunges_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.kettlebell_goblet_squat_exercise),
            false
        )
    )
}
