package com.migueldev.wodwiseapp.presentation.screen.user.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.di.Main
import com.migueldev.wodwiseapp.domain.exception.FirestoreConnectionException
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.domain.repository.login.LoginRepository
import com.migueldev.wodwiseapp.domain.usecase.EnableLoginButtonUseCase
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.UserTextResourceProvider
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
    private val userTextResourceProvider: UserTextResourceProvider,
    private val enableLoginButtonUseCase: EnableLoginButtonUseCase,
    private val loginRepository: LoginRepository,
    private val logger: Logger,
    private val userPreferences: UserPreferences,
    private val toastWrapper: ToastWrapper,
    @IO private val ioDispatcher: CoroutineDispatcher,
    @Main private val mainDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState: StateFlow<LoginState> = _loginState

    init {
        initializeTextResources()
    }

    private fun initializeTextResources() {
        _loginState.value = userTextResourceProvider.userInitializeTextResources()
    }

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
        navController: NavHostController,
        email: String,
        password: String,
        context: Context,
    ) {
        viewModelScope.launch(ioDispatcher) {
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
                    withContext(mainDispatcher) {
                        toastWrapper.show(_loginState.value.incorrectPasswordOrEmail)
                    }
                },
                ifRight = { firebaseUser ->
                    logger.d(
                        TAG,
                        context.getString(
                            R.string.log_msg_successful_login,
                            firebaseUser.email
                        )
                    )
                    userPreferences.saveUserEmail(firebaseUser.email ?: "")
                    withContext(mainDispatcher) {
                        navController.navigate(Routes.ScaffoldScreen.route)
                    }
                }
            )
        }
    }

    fun sendPasswordResetEmail(email: String) {
        viewModelScope.launch(ioDispatcher) {
            try {
                loginRepository.sendPasswordResetEmail(email)
                withContext(mainDispatcher) {
                    toastWrapper.show(_loginState.value.resetPassword)
                }
            } catch (e: FirestoreConnectionException) {
                logger.e(TAG, e.toString())
                withContext(mainDispatcher) {
                    toastWrapper.show(_loginState.value.incorrectRequest)
                }
            }
        }
    }
}

private const val TAG = "LoginViewModel"
