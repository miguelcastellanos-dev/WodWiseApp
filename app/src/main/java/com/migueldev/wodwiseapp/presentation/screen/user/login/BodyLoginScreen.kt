package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Email
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ForgotPasswordClickableText
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ImageLogo
import com.migueldev.wodwiseapp.presentation.screen.user.composables.LoginButton
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Password
import com.migueldev.wodwiseapp.presentation.screen.user.data.UserButtonParams

@Composable
fun BodyLoginScreen(
    appState: AppState,
    modifier: Modifier,
    context: Context,
    onSendPasswordResetEmailClicked: (String) -> Unit,
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            loginState = appState.loginState,
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        Email(
            loginState = appState.loginState,
            email = email
        ) {
            setEmail(it)
            appState.loginViewModel.onLoginChanged(
                email = it,
                password = password
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        Password(loginState = appState.loginState, password = password) {
            setPassword(it)
            appState.loginViewModel.onLoginChanged(
                email = email,
                password = it
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d16))
        ForgotPasswordClickableText(
            loginState = appState.loginState,
            modifier = Modifier.align(Alignment.End),
            onSendPasswordResetEmailClicked = onSendPasswordResetEmailClicked
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginButton(
            navController = appState.navController,
            loginState = appState.loginState,
            UserButtonParams(
                email = email,
                password = password,
                isEnabled = appState.loginState.isLoginEnabled
            ),
            loginViewModel = appState.loginViewModel,
            context = context
        )
    }
}
