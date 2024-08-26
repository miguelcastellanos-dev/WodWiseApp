package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createFunctionalWeightedExerciseData(
    resourceProvider: ResourceProvider,
): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.functional_weighted_sandbag_clean_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(
                R.string.functional_weighted_sandbag_bear_hug_carry_exercise
            ),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.functional_weighted_yoke_carry_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.functional_weighted_sled_pull_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.functional_weighted_sled_push_exercise),
            false
        ),

        CoachExerciseData(
            resourceProvider.getString(R.string.functional_weighted_wall_ball_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.functional_weighted_man_maker_exercise),
            false
        )
    )
}
