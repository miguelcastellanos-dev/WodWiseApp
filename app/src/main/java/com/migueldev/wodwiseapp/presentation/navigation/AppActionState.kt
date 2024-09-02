package com.migueldev.wodwiseapp.presentation.navigation

import android.content.Context

data class AppActionState(
    val onRequestPermissionAndRecordClicked: (Context) -> Unit,
    val onSaveWorkoutClicked: () -> Unit,
    val onToggleThemeClicked: () -> Unit,
    val onLogoutClicked: () -> Unit,
    val onDeleteIconClicked: (String) -> Unit,
    val onCheckboxClicked: (String, Boolean) -> Unit,
    val onRmChangeClicked: (String, Double) -> Unit,
    val onWeightClicked: (String) -> Unit,
)
