package com.migueldev.wodwiseapp.presentation.navigation

import android.content.Context

data class AppButtonsState(
    val onRequestPermissionAndRecordClicked: (Context) -> Unit,
    val onSaveWorkoutClicked: () -> Unit,
    val onToggleThemeClicked: () -> Unit,
    val onLogoutClicked: () -> Unit,
)
