package com.migueldev.wodwiseapp.presentation.screen.coach.utils

import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.ExerciseSection

fun getExerciseSections(coachState: CoachState): List<ExerciseSection> {
    return listOf(
        ExerciseSection(
            coachState.gymnasticsHeaderText,
            coachState.gymnasticCoachExerciseData
        ),
        ExerciseSection(
            coachState.strengthHeaderText,
            coachState.strengthCoachExerciseData
        ),
        ExerciseSection(
            coachState.barbellMovementsHeaderText,
            coachState.barbellMovementsCoachExerciseData
        ),
        ExerciseSection(
            coachState.metabolicHeaderText,
            coachState.metabolicCoachExerciseData
        ),
        ExerciseSection(
            coachState.dumbbellHeaderText,
            coachState.dumbbellCoachExerciseData
        ),
        ExerciseSection(
            coachState.kettlebellHeaderText,
            coachState.kettlebellCoachExerciseData
        ),
        ExerciseSection(
            coachState.functionalWeightedHeaderText,
            coachState.functionalWeightedCoachExerciseData
        ),
        ExerciseSection(
            coachState.bodyWeightHeaderText,
            coachState.bodyWeightCoachExerciseData
        )
    )
}
