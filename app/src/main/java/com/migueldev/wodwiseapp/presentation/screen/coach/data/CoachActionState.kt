package com.migueldev.wodwiseapp.presentation.screen.coach.data

data class CoachActionState(
    val onPreviousFormatClicked: () -> Unit,
    val onNextFormatClicked: () -> Unit,
    val onGenerateResponseClicked: () -> Unit,
    val onDismissResponseDialogClicked: () -> Unit,
    val onSaveResponseDialogClicked: () -> Unit,
    val onSelectedTimeChange: (Int) -> Unit,
    val onExerciseSelected: (String, Boolean) -> Unit,
)
