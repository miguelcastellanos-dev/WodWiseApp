package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun ForgotPasswordDialog(
    loginState: LoginState,
    onSendPasswordResetEmailClicked: (String) -> Unit,
) {
    var email by remember { mutableStateOf("") }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        onDismissRequest = {
            loginState.showForgotPasswordDialog.value = false
        },
        text = {
            ForgotPasswordDialogTextField(
                loginState = loginState,
                email = email,
                setEmail = { newValue -> email = newValue }
            )
        },
        confirmButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                ),
                onClick = {
                    onSendPasswordResetEmailClicked(email)
                    loginState.showForgotPasswordDialog.value = false
                },
                enabled = email.isNotBlank()
            ) {
                Text(
                    text = loginState.confirmSendEmailButtonText,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        dismissButton = {
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                onClick = {
                    loginState.showForgotPasswordDialog.value = false
                }
            ) {
                Text(
                    text = loginState.cancelSendEmailButtonText,
                    fontWeight = FontWeight.Bold
                )
            }
        },
        title = {
            Text(
                text = loginState.forgotPasswordTitleText,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    )
}
