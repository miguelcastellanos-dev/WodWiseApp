package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.dto.WeightDto

fun WeightDto.toMap(): Map<String, Any> {
    return mapOf(
        WEIGHT_ID_DATABASE_FIELD to weightId,
        WEIGHT_NAME_DATABASE_FIELD to nameExercise,
        WEIGHT_REPETITION_MAXIMUM_DATABASE_FIELD to repetitionMaximum
    )
}

private const val WEIGHT_NAME_DATABASE_FIELD = "nameExercise"
private const val WEIGHT_ID_DATABASE_FIELD = "weightId"
private const val WEIGHT_REPETITION_MAXIMUM_DATABASE_FIELD = "repetitionMaximum"
