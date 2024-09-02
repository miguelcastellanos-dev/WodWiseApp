package com.migueldev.wodwiseapp.presentation.screen.weight.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState

@Composable
fun AddWeightDialog(
    weightsState: WeightsState,
    onDismiss: () -> Unit,
    onConfirm: (String, Double) -> Unit,
) {
    weightsState.weightName.value = ""
    weightsState.weightRepetitionMaximum.value = ""

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        onDismissRequest = {
            onDismiss()
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    val rmValue = weightsState.weightRepetitionMaximum.value.toDoubleOrNull() ?: 0.0
                    onConfirm(weightsState.weightName.value, rmValue)
                },
                enabled = weightsState.weightName.value.isNotBlank() &&
                    weightsState.weightRepetitionMaximum.value.toDoubleOrNull() != null
            ) {
                Text(weightsState.confirmButtonDialogText)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    onDismiss()
                }
            ) {
                Text(weightsState.dismissButtonDialogText)
            }
        },
        title = {
            Text(
                text = weightsState.titleAddWeightDialogText,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        text = {
            TextFieldsAddWeightDialog(
                weightsState = weightsState,
                weightName = weightsState.weightName.value,
                setWeightName = { weightsState.weightName.value = it },
                weightRm = weightsState.weightRepetitionMaximum.value,
                setWeightRm = { weightsState.weightRepetitionMaximum.value = it }
            )
        }
    )
}
