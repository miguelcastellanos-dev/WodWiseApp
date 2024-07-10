package com.migueldev.wodwiseapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginScreen
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signUp.SignUpScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signUp.SignUpViewModel

@Composable
fun AppNavigation(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    signUpViewModel: SignUpViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Routes.LoginScreen.route
    ) {
        composable(Routes.LoginScreen.route) {
            LoginScreen(
                loginViewModel = loginViewModel,
                navController = navController
            )
        }
        composable(Routes.SignUpScreen.route) {
            SignUpScreen(
                signUpViewModel = signUpViewModel,
                navController = navController
            )
        }
    }
}
