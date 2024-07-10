package com.migueldev.wodwiseapp.model

sealed class Routes(val route: String) {
    data object LoginScreen : Routes("loginScreen")
    data object SignUpScreen : Routes("signUpScreen")
}
