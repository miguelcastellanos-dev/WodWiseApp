package com.migueldev.wodwiseapp.presentation.screen.user.data

data class SignUpFieldsParams(
    val email: String,
    val setEmail: (String) -> Unit,
    val password: String,
    val setPassword: (String) -> Unit,
    val confirmPassword: String,
    val setConfirmPassword: (String) -> Unit,
)

data class UserButtonParams(
    val email: String,
    val password: String,
    val isEnabled: Boolean,
)
