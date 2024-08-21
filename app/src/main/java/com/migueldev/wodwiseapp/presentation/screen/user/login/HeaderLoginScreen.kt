package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.app.Activity
import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun Header(
    loginState: LoginState,
    modifier: Modifier,
) {
    val activity = LocalContext.current as Activity
    Icon(
        imageVector = Icons.Default.Close,
        contentDescription = loginState.descriptionCloseAppIcon,
        modifier = modifier.clickable { activity.finish() },
        tint = MaterialTheme.colorScheme.primary
    )
}
