package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.KeyboardType
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun ForgotPasswordDialogTextField(
    loginState: LoginState,
    email: String,
    setEmail: (String) -> Unit,
) {
    TextField(
        value = email,
        onValueChange = { newValue -> setEmail(newValue) },
        label = {
            Text(
                text = loginState.hintEmail,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)
            )
        },
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Email
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.5f
            ),
            unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                alpha = 0.3f
            )
        )
    )
}
