package com.migueldev.wodwiseapp.presentation.screen.workout.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppButtonsState
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

@Composable
fun WorkoutSaveButton(
    modifier: Modifier,
    workoutState: WorkoutState,
    appButtonsState: AppButtonsState,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Button(
            onClick = { appButtonsState.onSaveWorkoutClicked() },
            enabled = workoutState.instructionsStateText.isNotBlank(),
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            Text(
                text = workoutState.saveWorkoutButtonText,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}
