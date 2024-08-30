package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createGymnasticExerciseData(resourceProvider: ResourceProvider): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_pull_up_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_chest_to_bar_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_handstand_push_up_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_bar_muscle_up_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_ring_muscle_up_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_toes_to_bar_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_dip_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_rope_climb_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_hand_stand_walk_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.gymnastic_pistol_squat_exercise),
            false
        )
    )
}
