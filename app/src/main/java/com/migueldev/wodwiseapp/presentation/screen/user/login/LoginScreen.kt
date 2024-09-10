package com.migueldev.wodwiseapp.presentation.screen.user.login

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun LoginScreen(
    appState: AppState,
    onSendPasswordResetEmailClicked: (String) -> Unit,
) {
    val context = LocalContext.current
    BackHandler {
        (context as? ComponentActivity)?.moveTaskToBack(true)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(Dimension.d16)
    ) {
        Header(
            loginState = appState.loginState,
            modifier = Modifier.align(Alignment.TopEnd)
        )
        BodyLoginScreen(
            appState = appState,
            modifier = Modifier.align(Alignment.TopCenter),
            context = context,
            onSendPasswordResetEmailClicked = onSendPasswordResetEmailClicked
        )
        Footer(
            loginState = appState.loginState,
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = appState.navController
        )
    }
}
