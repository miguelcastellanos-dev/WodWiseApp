package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Email
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ForgotPassword
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ImageLogo
import com.migueldev.wodwiseapp.presentation.screen.user.composables.LoginButton
import com.migueldev.wodwiseapp.presentation.screen.user.composables.LoginDivider
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Password
import com.migueldev.wodwiseapp.presentation.screen.user.composables.SocialLogin

@Composable
fun Body(
    modifier: Modifier,
    loginViewModel: LoginViewModel,
    context: Context,
    toastWrapper: ToastWrapper,
) {
    val loginState by loginViewModel.loginState.collectAsState()
    val (email, setEmail) = remember { mutableStateOf(loginState.email) }
    val (password, setPassword) = remember { mutableStateOf(loginState.password) }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        Email(email) {
            setEmail(it)
            loginViewModel.onLoginChanged(
                email = it,
                password = password
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        Password(password) {
            setPassword(it)
            loginViewModel.onLoginChanged(
                email = email,
                password = it
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d16))
        ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginButton(
            email = email,
            password = password,
            loginEnable = loginState.isLoginEnabled,
            loginViewModel = loginViewModel,
            context = context,
            toastWrapper = toastWrapper
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        LoginDivider()
        Spacer(modifier = Modifier.size(Dimension.d16))
        SocialLogin()
    }
}
