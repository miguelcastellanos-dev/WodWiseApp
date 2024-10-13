package com.migueldev.wodwiseapp.presentation.screen.calendar.data

data class CalendarState(
    val isLoading: Boolean = false,
    val checkboxState: Map<String, Boolean> = emptyMap(),
    val workoutsList: List<WorkoutCardData> = emptyList(),
    val showWeekCardState: Map<String, Boolean> = emptyMap(),
    val showDayCardState: Map<String, Boolean> = emptyMap(),
    val prefixTitleWeekText: String = "",
    val instructionTextTitleInCardView: String = "",
    val notesTextTitleInCardView: String = "",
    val calendarChangesSavedToast: String = "",
    val notesText: Map<String, String> = emptyMap(),
    val instructionsText: Map<String, String> = emptyMap(),
    val calendarInformationText: String = "",
    val calendarInformationTitleText: String = "",
)
