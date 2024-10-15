package com.migueldev.wodwiseapp.data.dto

import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightHistoryData

data class WeightDto(
    val weightId: String,
    val nameExercise: String,
    val repetitionMaximum: Double,
    val weightHistoryList: List<WeightHistoryData> = emptyList(),
)
