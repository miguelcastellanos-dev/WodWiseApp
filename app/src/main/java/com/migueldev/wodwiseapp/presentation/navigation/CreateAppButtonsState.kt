package com.migueldev.wodwiseapp.presentation.navigation

import android.Manifest
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.MainActivity
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun createAppButtonsState(
    appState: AppState,
): AppButtonsState {
    val coroutineScope = rememberCoroutineScope()
    with(appState) {
        return AppButtonsState(
            onRequestPermissionAndRecordClicked = { context ->
                val activity = context as? MainActivity
                activity?.requestPermissionLauncher?.launch(Manifest.permission.RECORD_AUDIO)
            },
            onSaveWorkoutClicked = {
                coroutineScope.launch {
                    workoutViewModel.saveWorkout(
                        instructions = workoutState.instructionsStateText,
                        session = workoutState.sessionStateText.displayName,
                        position = workoutState.positionStateText.displayName,
                        exerciseType = workoutState.exerciseTypeStateText.displayName,
                        datePickerState.selectedDateMillis
                    )
                }
            },
            onToggleThemeClicked = { scaffoldViewModel.switchTheme() },
            onLogoutClicked = {
                coroutineScope.launch {
                    appState.userPreferences.clearUserEmail()
                }
                appState.navController.navigate(Routes.LoginScreen.route) {
                    popUpTo(Routes.LoginScreen.route) { inclusive = true }
                }
            }
        )
    }
}
