package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.di.Main
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.domain.repository.login.LoginRepository
import com.migueldev.wodwiseapp.domain.usecase.EnableLoginButtonUseCase
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginStateData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val enableLoginButtonUseCase: EnableLoginButtonUseCase,
    private val loginRepository: LoginRepository,
    @IO private val ioDispatcher: CoroutineDispatcher,
    @Main private val mainDispatcher: CoroutineDispatcher,
    private val logger: Logger,
) : ViewModel() {
    private val _loginState = MutableStateFlow(LoginStateData())
    val loginState: StateFlow<LoginStateData> = _loginState

    fun onLoginChanged(email: String, password: String) {
        _loginState.update { currentState ->
            currentState.copy(
                email = email,
                password = password,
                isLoginEnabled = enableLoginButtonUseCase(email, password)
            )
        }
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        context: Context,
        toastWrapper: ToastWrapper,
    ) {
        viewModelScope.launch {
            val result = withContext(ioDispatcher) {
                loginRepository.login(email, password)
            }

            result.fold(
                ifLeft = { throwable ->
                    logger.e(
                        TAG,
                        context.getString(
                            R.string.log_msg_login_error,
                            throwable.message ?: R.string.unknown_error
                        )
                    )
                    toastWrapper.show(R.string.error_email_or_password)
                },
                ifRight = { firebaseUser ->
                    logger.d(
                        TAG,
                        context.getString(
                            R.string.log_msg_successful_login,
                            firebaseUser.email
                        )
                    )
                    withContext(mainDispatcher) {
                        Toast.makeText(context, "GO TO HOME SCREEN", Toast.LENGTH_LONG).show()
                        // Navigate to home screen
                    }
                }
            )
        }
    }
}

private const val TAG = "LoginViewModel"
