package com.migueldev.wodwiseapp.presentation.navigation

import android.content.Context
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData

data class AppActionState(
    val onRequestPermissionAndRecordClicked: (Context) -> Unit,
    val onSaveWorkoutClicked: () -> Unit,
    val onThemeSwitched: () -> Unit,
    val onLogoutClicked: () -> Unit,
    val onDeleteIconClicked: (String) -> Unit,
    val onCheckboxClicked: (String, Boolean) -> Unit,
    val onRmChangeClicked: (String, Double) -> Unit,
    val onWeightClicked: (String) -> Unit,
    val onWorkoutClicked: (WorkoutCardData) -> Unit,
    val onInstructionIconClicked: (String, String) -> Unit,
    val onNotesIconClicked: (String, String) -> Unit,
    val onSendPasswordResetEmailClicked: (String) -> Unit,
    val onBackClicked: () -> Unit,
    val onDeleteUserClicked: () -> Unit,
    val onDeleteAllWorkoutsClicked: () -> Unit,
    val onDeleteAllWeightsClicked: () -> Unit,
)
