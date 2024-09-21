package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun EmailInfoAlertDialog(
    loginState: LoginState,
    showInfoDialog: () -> Unit,
) {
    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        textContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
        onDismissRequest = { showInfoDialog() },
        title = { Text(loginState.emailInformationTitle) },
        text = {
            Text(loginState.emailInformationText)
        },
        confirmButton = {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = { showInfoDialog() }
            ) {
                Text(loginState.okReplyText)
            }
        }
    )
}
