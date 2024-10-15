package com.migueldev.wodwiseapp.presentation.screen.weight.data

import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightHistoryData

data class WeightData(
    val weightId: String,
    val weightName: String,
    val weightRepetitionMaximum: Double,
    val weightHistoryList: List<WeightHistoryData> = emptyList(),
)
