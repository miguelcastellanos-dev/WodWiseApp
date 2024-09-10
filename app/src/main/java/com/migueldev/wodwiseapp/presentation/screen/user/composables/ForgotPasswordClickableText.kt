package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun ForgotPasswordClickableText(
    loginState: LoginState,
    modifier: Modifier = Modifier,
    onSendPasswordResetEmailClicked: (String) -> Unit,
) {
    Text(
        text = loginState.forgotPasswordText,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.clickable {
            loginState.showForgotPasswordDialog.value = true
        }
    )

    if (loginState.showForgotPasswordDialog.value) {
        ForgotPasswordDialog(
            loginState = loginState,
            onSendPasswordResetEmailClicked = onSendPasswordResetEmailClicked
        )
    }
}
