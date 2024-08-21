package com.migueldev.wodwiseapp.domain.usecase

import javax.inject.Inject

class EnableSignUpButtonUseCase @Inject constructor() {

    operator fun invoke(
        email: String,
        password: String,
        confirmPassword: String,
    ): Boolean {
        val isEmailValid = email.matches(emailRegex)
        val isPasswordValid = password.length >= PASSWORD_LENGTH
        val doPasswordsMatch = password == confirmPassword
        return isEmailValid &&
            isPasswordValid &&
            doPasswordsMatch
    }
}

private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
private const val PASSWORD_LENGTH = 6
