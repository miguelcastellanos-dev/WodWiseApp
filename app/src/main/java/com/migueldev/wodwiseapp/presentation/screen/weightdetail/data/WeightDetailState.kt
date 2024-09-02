package com.migueldev.wodwiseapp.presentation.screen.weightdetail.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData

data class WeightDetailState(
    val weightRepetitionMaximum: MutableState<String> = mutableStateOf(""),
    val exercisesWeightList: List<WeightData> = emptyList(),
    val exerciseRmTitleText: String = "",
    val titlePercentagesViewDetailText: String = "",
    val percentageSignText: String = "",
    val titleRepsDetailViewText: String = "",
    val titleUpdateRmDialogText: String = "",
    val newRmDialogText: String = "",
    val confirmButtonDialogText: String = "",
    val dismissButtonDialogText: String = "",
    val emptyWeightText: String = "",
)
