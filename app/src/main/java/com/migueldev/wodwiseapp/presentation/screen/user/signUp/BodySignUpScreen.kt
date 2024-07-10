package com.migueldev.wodwiseapp.presentation.screen.user.signUp

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
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ConfirmPassword
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Email
import com.migueldev.wodwiseapp.presentation.screen.user.composables.ImageLogo
import com.migueldev.wodwiseapp.presentation.screen.user.composables.Password
import com.migueldev.wodwiseapp.presentation.screen.user.composables.SignUpButton
import com.migueldev.wodwiseapp.presentation.screen.user.composables.UserName

@Composable
fun Body(modifier: Modifier, signUpViewModel: SignUpViewModel) {
    val signupState by signUpViewModel.signUpState.collectAsState()

    Column(modifier = modifier) {
        Spacer(modifier = Modifier.size(Dimension.d32))
        ImageLogo(
            Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d16))
        Email(signupState.email) {
            signUpViewModel.onSignUpChanged(
                email = it,
                username = signupState.username,
                password = signupState.password,
                confirmPassword = signupState.confirmPassword
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        UserName(signupState.username) {
            signUpViewModel.onSignUpChanged(
                email = signupState.email,
                username = it,
                password = signupState.password,
                confirmPassword = signupState.confirmPassword
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        Password(signupState.password) {
            signUpViewModel.onSignUpChanged(
                email = signupState.email,
                username = signupState.username,
                password = it,
                confirmPassword = signupState.confirmPassword
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d8))
        ConfirmPassword(signupState.confirmPassword) {
            signUpViewModel.onSignUpChanged(
                email = signupState.email,
                username = signupState.username,
                password = signupState.password,
                confirmPassword = it
            )
        }
        Spacer(modifier = Modifier.size(Dimension.d16))
        SignUpButton(signupState.isSignUpEnabled)
        Spacer(modifier = Modifier.size(Dimension.d16))
    }
}
