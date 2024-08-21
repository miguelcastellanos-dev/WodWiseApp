package com.migueldev.wodwiseapp.presentation.screen.user.signup

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.domain.repository.login.SignUpRepository
import com.migueldev.wodwiseapp.domain.usecase.EnableSignUpButtonUseCase
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val enableSignUpButtonUseCase: EnableSignUpButtonUseCase,
    private val signUpRepository: SignUpRepository,
    private val logger: Logger,
    private val toastWrapper: ToastWrapper,
    private val resourceProvider: ResourceProvider,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpState())
    val signUpState: StateFlow<SignUpState> = _signUpState

    init {
        initializeTextResources()
    }

    private fun initializeTextResources() {
        _signUpState.update { currentState ->
            currentState.copy(
                signupButtonText = resourceProvider.getString(
                    R.string.signUp_button_text
                ),
                loginQuestion = resourceProvider.getString(
                    R.string.login_question
                ),
                clickableLoginText = resourceProvider.getString(
                    R.string.clickable_login_text
                )
            )
        }
    }

    fun onSignUpChanged(
        email: String,
        password: String,
        confirmPassword: String,
    ) {
        _signUpState.update { currentState ->
            currentState.copy(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                isSignUpEnabled = enableSignUpButtonUseCase(
                    email,
                    password,
                    confirmPassword
                )
            )
        }
    }

    fun registerWithEmailAndPassword(
        email: String,
        password: String,
        navController: NavHostController,
        context: Context,
    ) {
        viewModelScope.launch {
            val result = withContext(ioDispatcher) {
                signUpRepository.register(email, password)
            }

            result.fold(
                ifLeft = { throwable ->
                    logger.e(
                        TAG,
                        context.getString(
                            R.string.log_msg_register_exception,
                            throwable.message ?: R.string.unknown_error
                        )
                    )
                },
                ifRight = { firebaseUser ->
                    logger.d(
                        TAG,
                        context.getString(
                            R.string.log_msg_successful_register,
                            firebaseUser.email
                        )
                    )
                    navController.navigate(Routes.LoginScreen.route)
                    toastWrapper.show(R.string.toast_msg_registered_user)
                }
            )
        }
    }
}

private const val TAG = "SignUpViewModel"
