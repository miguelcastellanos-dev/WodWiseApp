package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.migueldev.wodwiseapp.R

@Composable
fun LoginButton(loginEnable: Boolean) {
    Button(
        onClick = { },
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = R.string.login_button_text),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun SignUpButton(loginEnable: Boolean) {
    Button(
        onClick = {},
        enabled = loginEnable,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.secondary,
            contentColor = Color.White,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = R.string.signUp_button_text),
            style = MaterialTheme.typography.titleLarge
        )
    }
}
