package com.migueldev.wodwiseapp.data.remote.datasource.signup

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.data.remote.service.AuthService
import com.migueldev.wodwiseapp.domain.exception.RegistrationFailedException
import javax.inject.Inject

class RemoteSignUpDatasource @Inject constructor(
    private val authService: AuthService,
) : SignUpDatasource {
    override suspend fun registerWithEmail(
        email: String,
        password: String,
    ): Either<Throwable, FirebaseUser> {
        return Either.catch {
            val user = authService.register(email, password)
            user ?: throw RegistrationFailedException()
        }
    }
}
