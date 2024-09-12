package com.migueldev.wodwiseapp.presentation.screen.profile.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState

@Composable
fun WarningAlertDialog(
    profileState: ProfileState,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
    warningText: String,
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        onDismissRequest = { onDismiss() },
        title = {
            Text(
                text = profileState.warningDialogTitle,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        text = {
            Text(text = warningText)
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    onClick()
                    onDismiss()
                }
            ) {
                Text(profileState.warningDialogConfirmButton)
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { onDismiss() }
            ) {
                Text(profileState.warningDialogCancelButton)
            }
        }
    )
}
