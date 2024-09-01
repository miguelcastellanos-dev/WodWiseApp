package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.remote.response.WeightResponse
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData

fun WeightResponse.toDomain(): WeightData {
    return WeightData(
        weightId = this.weightId,
        weightName = this.nameExercise,
        weightRepetitionMaximum = this.repetitionMaximum
    )
}
