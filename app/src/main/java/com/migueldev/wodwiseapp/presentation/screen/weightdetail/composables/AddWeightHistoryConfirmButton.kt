package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import android.util.Log
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.navigation.AppState

@Composable
fun AddWeightHistoryConfirmButton(
    appState: AppState,
    isDateValid: Boolean,
    addWeightHistoryClicked: (String, Double, Int, String) -> Unit,
    weightId: String,
    enteredDate: String,
    onDismiss: () -> Unit,
) {
    Button(
        onClick = {
            val weight = appState.weightDetailState.weightHistory.value.toDoubleOrNull() ?: 0.0
            val repetitions = appState.weightDetailState.repetitionsHistory.value.toIntOrNull()

            if (repetitions != null && isDateValid) {
                addWeightHistoryClicked(
                    weightId,
                    weight,
                    repetitions,
                    enteredDate
                )
                onDismiss()
            } else {
                Log.e("AddWeightHistory", "Repetitions or Date is invalid.")
            }
        },
        enabled = appState.weightDetailState.weightHistory.value.isNotBlank() &&
            appState.weightDetailState.repetitionsHistory.value.toIntOrNull() != null &&
            isDateValid,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(appState.weightState.confirmButtonDialogText)
    }
}
