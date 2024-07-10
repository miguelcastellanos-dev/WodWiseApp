package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _loginState = MutableStateFlow(LoginStateData())
    val loginState: StateFlow<LoginStateData> = _loginState

    private fun enableLogin(email: String, password: String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > PASSWORD_LENGTH

    fun onLoginChanged(email: String, password: String) {
        _loginState.update { currentState ->
            currentState.copy(
                email = email,
                password = password,
                isLoginEnabled = enableLogin(email, password)
            )
        }
    }
}

private const val PASSWORD_LENGTH = 6
