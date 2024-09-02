package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun UpdateRmDialog(
    weightDetailState: WeightDetailState,
    initialValue: String,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {
    weightDetailState.weightRepetitionMaximum.value = initialValue

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        onDismissRequest = { onDismiss() },
        title = { Text(weightDetailState.titleUpdateRmDialogText) },
        text = {
            Column {
                Text(
                    text = weightDetailState.newRmDialogText,
                    color = MaterialTheme.colorScheme.onSurface
                )
                TextField(
                    value = weightDetailState.weightRepetitionMaximum.value,
                    onValueChange = { weightDetailState.weightRepetitionMaximum.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer
                    )
                )
            }
        },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    onConfirm(weightDetailState.weightRepetitionMaximum.value)
                    onDismiss()
                }
            ) { Text(weightDetailState.confirmButtonDialogText) }
        },
        dismissButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    onDismiss()
                }
            ) {
                Text(weightDetailState.dismissButtonDialogText)
            }
        }
    )
}
