package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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
                OutlinedTextField(
                    value = weightDetailState.weightRepetitionMaximum.value,
                    label = { Text(weightDetailState.newRmDialogText) },
                    onValueChange = { weightDetailState.weightRepetitionMaximum.value = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
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
