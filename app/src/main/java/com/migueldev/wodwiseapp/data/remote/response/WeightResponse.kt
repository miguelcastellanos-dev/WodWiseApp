package com.migueldev.wodwiseapp.data.remote.response

import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightHistoryData

data class WeightResponse(
    val weightId: String = "",
    val nameExercise: String = "",
    val repetitionMaximum: Double = 0.0,
    val weightHistoryList: List<WeightHistoryData> = emptyList(),
)
