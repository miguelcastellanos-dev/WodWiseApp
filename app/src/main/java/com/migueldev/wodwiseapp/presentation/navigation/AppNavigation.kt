package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signUp.SignUpViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel,
    toastWrapper: ToastWrapper,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route
    ) {
        loginScreen(loginViewModel, navController, toastWrapper)
        signUpScreen(signUpViewModel, navController, toastWrapper)
    }
}
