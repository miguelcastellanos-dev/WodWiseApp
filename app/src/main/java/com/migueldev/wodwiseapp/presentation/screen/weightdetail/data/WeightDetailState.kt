package com.migueldev.wodwiseapp.presentation.screen.weightdetail.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData

data class WeightDetailState(
    val weightHistory: MutableState<String> = mutableStateOf(""),
    val repetitionsHistory: MutableState<String> = mutableStateOf(""),
    val selectedDate: String = "",
    val weightRepetitionMaximum: MutableState<String> = mutableStateOf(""),
    val exercisesWeightList: List<WeightData> = emptyList(),
    val showAddWeightDetailDialog: MutableState<Boolean> = mutableStateOf(false),
    val exerciseRmTitleText: String = "",
    val titlePercentagesViewDetailText: String = "",
    val percentageSignText: String = "",
    val titleRepsDetailViewText: String = "",
    val titleUpdateRmDialogText: String = "",
    val newRmDialogText: String = "",
    val confirmButtonDialogText: String = "",
    val dismissButtonDialogText: String = "",
    val emptyWeightText: String = "",
    val dateInputTextFieldLabel: String = "",
    val dateInputTextFieldPlaceholder: String = "",
    val weightHistoryDialogTitle: String = "",
    val weightInputTextFieldLabel: String = "",
    val weightInputTextFieldPlaceholder: String = "",
    val repetitionsInputTextFieldLabel: String = "",
    val repetitionsInputTextFieldPlaceholder: String = "",
    val repsText: String = "",
    val historyListTitle: String = "",
    val emptyHistoryList: String = "",
    val abbreviationsForUnitsOfWeight: String = "",
    val openingParenthesis: String = "",
    val closingParenthesis: String = "",
)
