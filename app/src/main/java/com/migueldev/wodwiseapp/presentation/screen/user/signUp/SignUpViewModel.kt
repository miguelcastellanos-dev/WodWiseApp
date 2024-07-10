package com.migueldev.wodwiseapp.presentation.screen.user.signUp

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpStateData())
    val signUpState: StateFlow<SignUpStateData> = _signUpState

    fun onSignUpChanged(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ) {
        _signUpState.update { currentState ->
            currentState.copy(
                email = email,
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                isSignUpEnabled = enableSignUpButton(email, username, password, confirmPassword)
            )
        }
    }

    private fun enableSignUpButton(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isUsernameValid = username.length >= USERNAME_LENGTH
        val isPasswordValid = password.length >= PASSWORD_LENGTH
        val doPasswordsMatch = password == confirmPassword

        return isEmailValid && isUsernameValid && isPasswordValid && doPasswordsMatch
    }
}

private const val PASSWORD_LENGTH = 6
private const val USERNAME_LENGTH = 4
