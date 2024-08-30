package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData

fun createMetabolicExerciseData(resourceProvider: ResourceProvider): List<CoachExerciseData> {
    return listOf(
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_shuttle_run_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_calories_bikeErg_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_calories_row_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_calories_echo_bike_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_calories_ski_exercise),
            false
        ),

        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_burpees_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_burpee_to_target_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_burpee_box_jump_over_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_burpee_over_the_bar_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_box_jumps_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_box_jump_over_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_single_under_jump_rope_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_double_under_jump_rope_exercise),
            false
        ),
        CoachExerciseData(
            resourceProvider.getString(R.string.metabolic_crossover_jump_rope_exercise),
            false
        )
    )
}
