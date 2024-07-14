package com.migueldev.wodwiseapp.presentation.screen.user.composables

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signUp.SignUpViewModel

@Composable
fun LoginButton(
    email: String,
    password: String,
    loginEnable: Boolean,
    loginViewModel: LoginViewModel,
    context: Context,
    toastWrapper: ToastWrapper,
) {
    Button(
        onClick = {
            loginViewModel.signInWithEmailAndPassword(
                email = email,
                password = password,
                context = context,
                toastWrapper = toastWrapper
            )
        },
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
fun SignUpButton(
    email: String,
    password: String,
    loginEnable: Boolean,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
    context: Context,
    toastWrapper: ToastWrapper,
) {
    Button(
        onClick = {
            signUpViewModel.registerWithEmailAndPassword(
                email = email,
                password = password,
                navController = navController,
                context = context,
                toastWrapper = toastWrapper
            )
        },
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
