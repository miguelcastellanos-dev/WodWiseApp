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
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Email
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ForgotPassword
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ImageLogo
import com.migueldev.wodwiseapp.presentation.screen.user.composables.LoginButton
import com.migueldev.wodwiseapp.presentation.screen.user.composables.LoginDivider
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Password
import com.migueldev.wodwiseapp.presentation.screen.user.composables.SocialLogin
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.UserButtonParams

@Composable
fun BodyLoginScreen(
    loginState: LoginState,
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    context: Context,
) {
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            loginState = loginState,
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        Email(
            loginState = loginState,
            email = email
        ) {
            setEmail(it)
            loginViewModel.onLoginChanged(
                email = it,
                password = password
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        Password(loginState = loginState, password = password) {
            setPassword(it)
            loginViewModel.onLoginChanged(
                email = email,
                password = it
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d16))
        ForgotPassword(loginState = loginState, Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginButton(
            loginState = loginState,
            UserButtonParams(
                email = email,
                password = password,
                isEnabled = loginState.isLoginEnabled
            ),
            loginViewModel = loginViewModel,
            context = context
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginDivider(loginState = loginState)
        Spacer(modifier = Modifier.size(Dimension.d16))
        SocialLogin(loginState = loginState)
    }
}
