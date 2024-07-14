package com.migueldev.wodwiseapp.domain.usecase

import javax.inject.Inject

class EnableLoginButtonUseCase @Inject constructor() {

    operator fun invoke(email: String, password: String): Boolean {
        return email.matches(emailRegex) && password.length >= PASSWORD_LENGTH
    }
}

private val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()
private const val PASSWORD_LENGTH = 6
