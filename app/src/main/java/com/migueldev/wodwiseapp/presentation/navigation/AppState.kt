package com.migueldev.wodwiseapp.presentation.navigation

import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel

data class AppState(
    val loginViewModel: LoginViewModel,
    val signUpViewModel: SignUpViewModel,
    val loginState: LoginState,
    val signUpState: SignUpState,
    val navController: NavHostController,
)
