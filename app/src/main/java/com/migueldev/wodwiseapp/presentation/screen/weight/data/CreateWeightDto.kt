package com.migueldev.wodwiseapp.presentation.screen.weight.data

import com.migueldev.wodwiseapp.data.dto.WeightDto

fun createWeightDto(
    weightId: String,
    nameExercise: String,
    rm: Double,
): WeightDto {
    return WeightDto(
        weightId = weightId,
        nameExercise = nameExercise,
        repetitionMaximum = rm
    )
}
