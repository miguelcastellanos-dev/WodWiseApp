package com.migueldev.wodwiseapp.presentation.screen.user.composables

import android.content.Context
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.UserButtonParams
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel

@Composable
fun LoginButton(
    navController: NavHostController,
    loginState: LoginState,
    params: UserButtonParams,
    loginViewModel: LoginViewModel,
    context: Context,
) {
    Button(
        onClick = {
            loginViewModel.signInWithEmailAndPassword(
                email = params.email,
                password = params.password,
                context = context,
                navController = navController
            )
        },
        enabled = params.isEnabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = loginState.loginButtonText,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Composable
fun SignUpButton(
    loginState: LoginState,
    params: UserButtonParams,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
    context: Context,
) {
    Button(
        onClick = {
            signUpViewModel.registerWithEmailAndPassword(
                email = params.email,
                password = params.password,
                navController = navController,
                context = context
            )
        },
        enabled = params.isEnabled,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Text(
            text = loginState.signupButtonText,
            style = MaterialTheme.typography.titleLarge
        )
    }
}
