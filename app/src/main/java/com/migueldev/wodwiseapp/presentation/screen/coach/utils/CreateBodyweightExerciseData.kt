package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createBodyWeightExerciseData(resourceProvider: ResourceProvider): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_air_squat_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_jumping_squat_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_walking_lunges_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_sit_ups_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_mountain_climbers_exercise),
            false
        ),

        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_bear_crawl_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_broad_jump_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_ghd_sit_up_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_plank_hold_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_hollow_body_hold_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_hollow_rock_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_russian_twist_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_superman_hold_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_v_ups_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.body_weight_side_plank_exercise),
            false
        )
    )
}
