package com.migueldev.wodwiseapp.presentation.screen.user.data

data class SignUpState(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSignUpEnabled: Boolean = false,
    val isLoading: Boolean = false,
    val signupButtonText: String = "",
    val loginQuestion: String = "",
    val clickableLoginText: String = "",
)
