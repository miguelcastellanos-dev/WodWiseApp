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
fun createAppActionState(
    appState: AppState,
): AppActionState {
    val coroutineScope = rememberCoroutineScope()
    with(appState) {
        return AppActionState(
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
            onThemeSwitched = { settingViewModel.switchTheme() },
            onLogoutClicked = {
                coroutineScope.launch {
                    userPreferences.clearUserEmail()
                }
                navController.navigate(Routes.LoginScreen.route) {
                    popUpTo(Routes.LoginScreen.route) { inclusive = true }
                }
            },
            onDeleteIconClicked = { workoutId ->
                calendarViewModel.deleteWorkoutItem(workoutId)
            },
            onCheckboxClicked = { workoutId, newState ->
                calendarViewModel.updateCheckboxState(
                    workoutId = workoutId,
                    newState = newState
                )
            },
            onRmChangeClicked = { weightId, newRm ->
                weightDetailViewModel.updateRmInCoroutine(weightId, newRm)
            },
            onWeightClicked = { weightId ->
                weightViewModel.weightToDetail(navController, weightId)
            },
            onWorkoutClicked = calendarViewModel.sendWorkoutToDetail(navController),
            onInstructionIconClicked = { workoutId, newInstructions ->
                calendarViewModel.updateInstructionsText(workoutId, newInstructions)
            },
            onNotesIconClicked = { workoutId, newNotes ->
                calendarViewModel.updateNotesText(workoutId, newNotes)
            },
            onSendPasswordResetEmailClicked = { email ->
                loginViewModel.sendPasswordResetEmail(email)
            },
            onBackClicked = { appState.navController.popBackStack() },
            onDeleteUserClicked = {
                appState.profileViewModel.deleteUser()
                appState.profileViewModel.deleteAllWorkouts()
                appState.profileViewModel.deleteAllWeights()
                coroutineScope.launch {
                    userPreferences.clearUserEmail()
                }
                navController.navigate(Routes.LoginScreen.route)
            },
            onDeleteAllWorkoutsClicked = {
                appState.profileViewModel.deleteAllWorkouts()
            },
            onDeleteAllWeightsClicked = {
                appState.profileViewModel.deleteAllWeights()
            }
        )
    }
}
