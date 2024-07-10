package com.migueldev.wodwiseapp.presentation.screen.user.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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

@Composable
fun Body(modifier: Modifier, loginViewModel: LoginViewModel) {
    val loginState by loginViewModel.loginState.collectAsState()

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        Email(loginState.email) {
            loginViewModel.onLoginChanged(email = it, password = loginState.password)
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        Password(loginState.password) {
            loginViewModel.onLoginChanged(email = loginState.email, password = it)
        }
        Spacer(modifier = Modifier.size(Dimension.d16))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginButton(loginState.isLoginEnabled)
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginDivider()
        Spacer(modifier = Modifier.size(Dimension.d16))
        SocialLogin()
    }
}
