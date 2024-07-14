package com.migueldev.wodwiseapp.presentation.screen.user.signUp

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.di.Main
import com.migueldev.wodwiseapp.domain.logger.Logger
import com.migueldev.wodwiseapp.domain.repository.login.SignUpRepository
import com.migueldev.wodwiseapp.domain.usecase.EnableSignUpButtonUseCase
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpStateData
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
    @IO private val ioDispatcher: CoroutineDispatcher,
    @Main private val mainDispatcher: CoroutineDispatcher,
    private val logger: Logger,
) : ViewModel() {

    private val _signUpState = MutableStateFlow(SignUpStateData())
    val signUpState: StateFlow<SignUpStateData> = _signUpState

    fun onSignUpChanged(
        email: String,
        username: String,
        password: String,
        confirmPassword: String,
    ) {
        _signUpState.update { currentState ->
            currentState.copy(
                email = email,
                username = username,
                password = password,
                confirmPassword = confirmPassword,
                isSignUpEnabled = enableSignUpButtonUseCase(
                    email,
                    username,
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
        toastWrapper: ToastWrapper,
    ) {
        viewModelScope.launch(ioDispatcher) {
            signUpRepository.register(email, password).fold(
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
                    withContext(mainDispatcher) {
                        navController.navigate(Routes.LoginScreen.route)
                        toastWrapper.show(R.string.toast_msg_registered_user)
                    }
                }
            )
        }
    }
}

private const val TAG = "SignUpViewModel"
