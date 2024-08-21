package com.migueldev.wodwiseapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginScreen
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel

fun NavGraphBuilder.loginScreen(
    loginState: LoginState,
    loginViewModel: LoginViewModel,
    navController: NavHostController,
) {
    composable(Routes.LoginScreen.route) {
        LoginScreen(
            loginState = loginState,
            loginViewModel = loginViewModel,
            navController = navController
        )
    }
}

fun NavGraphBuilder.signUpScreen(
    loginState: LoginState,
    signUpState: SignUpState,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
) {
    composable(Routes.SignUpScreen.route) {
        SignUpScreen(
            loginState = loginState,
            signUpState = signUpState,
            signUpViewModel = signUpViewModel,
            navController = navController
        )
    }
}
