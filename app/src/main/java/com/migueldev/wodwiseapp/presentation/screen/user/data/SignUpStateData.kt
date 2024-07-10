package com.migueldev.wodwiseapp.presentation.screen.user.data

data class SignUpStateData(
    val email: String = "",
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isSignUpEnabled: Boolean = false,
    val isLoading: Boolean = false
)
