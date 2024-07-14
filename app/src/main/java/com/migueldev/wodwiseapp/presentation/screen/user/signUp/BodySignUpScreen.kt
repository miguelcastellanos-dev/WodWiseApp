package com.migueldev.wodwiseapp.presentation.screen.user.signUp

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
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ConfirmPassword
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Email
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ImageLogo
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Password
import com.migueldev.wodwiseapp.presentation.screen.user.composables.SignUpButton
import com.migueldev.wodwiseapp.presentation.screen.user.composables.UserName

@Composable
fun Body(
    modifier: Modifier,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
    context: Context,
    toastWrapper: ToastWrapper,
) {
    val signUpState by signUpViewModel.signUpState.collectAsState()
    val (email, setEmail) = remember { mutableStateOf(signUpState.email) }
    val (username, setUsername) = remember { mutableStateOf(signUpState.username) }
    val (password, setPassword) = remember { mutableStateOf(signUpState.password) }
    val (confirmPassword, setConfirmPassword) = remember {
        mutableStateOf(signUpState.confirmPassword)
    }

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        InputFieldsSection(
            email = email,
            setEmail = setEmail,
            username = username,
            setUsername = setUsername,
            password = password,
            setPassword = setPassword,
            confirmPassword = confirmPassword,
            setConfirmPassword = setConfirmPassword,
            signUpViewModel = signUpViewModel
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        SignUpButton(
            email = email,
            password = password,
            loginEnable = signUpState.isSignUpEnabled,
            signUpViewModel = signUpViewModel,
            navController = navController,
            context = context,
            toastWrapper = toastWrapper
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
    }
}

@Composable
fun InputFieldsSection(
    email: String,
    setEmail: (String) -> Unit,
    username: String,
    setUsername: (String) -> Unit,
    password: String,
    setPassword: (String) -> Unit,
    confirmPassword: String,
    setConfirmPassword: (String) -> Unit,
    signUpViewModel: SignUpViewModel,
) {
    Email(email) {
        setEmail(it)
        signUpViewModel.onSignUpChanged(
            email = it,
            username = username,
            password = password,
            confirmPassword = confirmPassword
        )
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
    UserName(username) {
        setUsername(it)
        signUpViewModel.onSignUpChanged(
            email = email,
            username = it,
            password = password,
            confirmPassword = confirmPassword
        )
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
    Password(password) {
        setPassword(it)
        signUpViewModel.onSignUpChanged(
            email = email,
            username = username,
            password = it,
            confirmPassword = confirmPassword
        )
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
    ConfirmPassword(confirmPassword) {
        setConfirmPassword(it)
        signUpViewModel.onSignUpChanged(
            email = email,
            username = username,
            password = password,
            confirmPassword = it
        )
    }
}
