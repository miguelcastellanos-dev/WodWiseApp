package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.dto.WeightDto

fun WeightDto.toMap(): Map<String, Any> {
    return mapOf(
        WEIGHT_ID_DATABASE_FIELD to weightId,
        WEIGHT_NAME_DATABASE_FIELD to nameExercise,
        WEIGHT_REPETITION_MAXIMUM_DATABASE_FIELD to repetitionMaximum,
        WEIGHT_HISTORY_LIST_DATABASE_FIELD to weightHistoryList.map {
            mapOf(
                DATE_HISTORY_DATABASE_FIELD to it.dateHistory,
                REPETITIONS_HISTORY_LIST_DATABASE_FIELD to it.repetitionsHistory,
                WEIGHT_HISTORY_DATABASE_FIELD to it.weightHistory,
                ID_HISTORY_DATABASE_FIELD to it.idHistory
            )
        }
    )
}

private const val WEIGHT_NAME_DATABASE_FIELD = "nameExercise"
private const val WEIGHT_ID_DATABASE_FIELD = "weightId"
const val WEIGHT_REPETITION_MAXIMUM_DATABASE_FIELD = "repetitionMaximum"
const val WEIGHT_HISTORY_LIST_DATABASE_FIELD = "weightHistoryList"
const val ID_HISTORY_DATABASE_FIELD = "idHistory"
const val DATE_HISTORY_DATABASE_FIELD = "dateHistory"
const val REPETITIONS_HISTORY_LIST_DATABASE_FIELD = "repetitionsHistory"
const val WEIGHT_HISTORY_DATABASE_FIELD = "weightHistory"
