package com.migueldev.wodwiseapp.presentation.screen.user.signup

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
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ConfirmPassword
import com.migueldev.wodwiseapp.presentation.screen.user.composables.EmailTextField
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ImageLogo
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Password
import com.migueldev.wodwiseapp.presentation.screen.user.composables.SignUpButton
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpFieldsParams
import com.migueldev.wodwiseapp.presentation.screen.user.data.UserButtonParams

@Composable
fun BodySignUpScreen(
    loginState: LoginState,
    modifier: Modifier,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
    context: Context,
) {
    val signUpState by signUpViewModel.signUpState.collectAsState()
    val (email, setEmail) = remember { mutableStateOf("") }
    val (password, setPassword) = remember { mutableStateOf("") }
    val (confirmPassword, setConfirmPassword) = remember {
        mutableStateOf("")
    }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            loginState = loginState,
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        InputFieldsSection(
            loginState = loginState,
            SignUpFieldsParams(
                email = email,
                setEmail = setEmail,
                password = password,
                setPassword = setPassword,
                confirmPassword = confirmPassword,
                setConfirmPassword = setConfirmPassword
            ),
            signUpViewModel = signUpViewModel
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        SignUpButton(
            loginState = loginState,
            UserButtonParams(
                email = email,
                password = password,
                isEnabled = signUpState.isSignUpEnabled
            ),
            signUpViewModel = signUpViewModel,
            navController = navController,
            context = context
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
    }
}

@Composable
fun InputFieldsSection(
    loginState: LoginState,
    params: SignUpFieldsParams,
    signUpViewModel: SignUpViewModel,
) {
    EmailTextField(loginState, params.email) {
        params.setEmail(it)
        signUpViewModel.onSignUpChanged(
            email = it,
            password = params.password,
            confirmPassword = params.confirmPassword
        )
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
    Password(loginState, params.password) {
        params.setPassword(it)
        signUpViewModel.onSignUpChanged(
            email = params.email,
            password = it,
            confirmPassword = params.confirmPassword
        )
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
    ConfirmPassword(loginState, params.confirmPassword) {
        params.setConfirmPassword(it)
        signUpViewModel.onSignUpChanged(
            email = params.email,
            password = params.password,
            confirmPassword = it
        )
    }
}
