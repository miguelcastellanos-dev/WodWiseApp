package com.migueldev.wodwiseapp.presentation.screen.coach.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CoachState(
    val isLoading: Boolean = false,
    val selectedTime: Int = INITIAL_SELECTED_TIME,
    val selectedFormat: String = INITIAL_SELECTED_FORMAT,
    val selectedExerciseLists: Set<String> = emptySet(),
    val timeTitleText: String = "",
    val systemInfoText: String = "",
    val sessionText: String = "",
    val positionText: String = "",
    val exerciseTypeText: String = "",
    val savedWorkoutToastText: String = "",
    val formatListTexts: List<String> = emptyList(),
    val suffixMinutes: String = "",
    val addMinutes: String = "",
    val subtractMinutes: String = "",
    val nextFormatArrow: String = "",
    val previousFormatArrow: String = "",
    val notesInitialText: String = "",
    val generateResponseButtonText: String = "",
    val showResponseDialog: MutableState<Boolean> = mutableStateOf(false),
    val responseDialogTitleText: String = "",
    val responseDialogExitButtonText: String = "",
    val responseDialogSaveButtonText: String = "",
    val answerOpenAi: String? = null,
    val formatTitleText: String = "",
    val showExerciseCardState: Map<String, Boolean> = emptyMap(),
    val barbellMovementsCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val barbellMovementsHeaderText: String = "",
    val bodyWeightCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val bodyWeightHeaderText: String = "",
    val metabolicCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val metabolicHeaderText: String = "",
    val dumbbellCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val dumbbellHeaderText: String = "",
    val functionalWeightedCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val functionalWeightedHeaderText: String = "",
    val gymnasticCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val kettlebellHeaderText: String = "",
    val kettlebellCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val gymnasticsHeaderText: String = "",
    val strengthCoachExerciseData: List<CoachExerciseData> = emptyList(),
    val strengthHeaderText: String = "",
)

private const val INITIAL_SELECTED_TIME = 20
private const val INITIAL_SELECTED_FORMAT = "AMRAP"
