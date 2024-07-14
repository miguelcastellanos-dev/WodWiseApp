package com.migueldev.wodwiseapp.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginScreen
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signUp.SignUpScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signUp.SignUpViewModel

fun NavGraphBuilder.loginScreen(
    loginViewModel: LoginViewModel,
    navController: NavHostController,
    toastWrapper: ToastWrapper,
) {
    composable(Routes.LoginScreen.route) {
        LoginScreen(
            loginViewModel = loginViewModel,
            navController = navController,
            toastWrapper = toastWrapper
        )
    }
}

fun NavGraphBuilder.signUpScreen(
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
    toastWrapper: ToastWrapper,
) {
    composable(Routes.SignUpScreen.route) {
        SignUpScreen(
            signUpViewModel = signUpViewModel,
            navController = navController,
            toastWrapper = toastWrapper
        )
    }
}
