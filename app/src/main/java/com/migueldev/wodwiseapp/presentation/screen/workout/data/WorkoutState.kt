package com.migueldev.wodwiseapp.presentation.screen.workout.data

data class WorkoutState(
    val instructionsStateText: String = "",
    val saveWorkoutButtonText: String = "",
    val trainingInfoSystemInstructionsText: String = "",
    val savedWorkoutToastText: String = "",
    val isLoading: Boolean = false,
    val isRecording: Boolean = false,
    val stopRecordingText: String = "",
    val startRecordingText: String = "",
    val placeholderInstructionsText: String = "",
    val selectDateTitleText: String = "",
    val sessionStateText: SessionItem = SessionItem(""),
    val positionStateText: PositionSessionItem = PositionSessionItem(""),
    val exerciseTypeStateText: ExerciseTypeItem = ExerciseTypeItem(""),
    val sessionItems: List<SessionItem> = emptyList(),
    val positionSessionItems: List<PositionSessionItem> = emptyList(),
    val exerciseTypeItems: List<ExerciseTypeItem> = emptyList(),
    val isRecorderButtonEnabled: Boolean = true,
    val isDatePickerVisible: Boolean = false,
)

data class SessionItem(val displayName: String)
data class PositionSessionItem(val displayName: String)
data class ExerciseTypeItem(val displayName: String)
