package com.migueldev.wodwiseapp.presentation.screen.weight.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class WeightsState(
    val isLoading: Boolean = false,
    val exercisesWeightList: List<WeightData> = emptyList(),
    val showAddWeightDialog: MutableState<Boolean> = mutableStateOf(false),
    val weightName: MutableState<String> = mutableStateOf(""),
    val weightRepetitionMaximum: MutableState<String> = mutableStateOf(""),
    val emptyWeightText: String = "",
    val confirmButtonDialogText: String = "",
    val dismissButtonDialogText: String = "",
    val titleAddWeightDialogText: String = "",
    val textFieldAddExerciseNameDialogText: String = "",
    val textFieldAddRmWeightDialogText: String = "",
    val contentDescriptionFab: String = "",
    val weightInformationTitleText: String = "",
    val weightInformationText: String = "",
)
