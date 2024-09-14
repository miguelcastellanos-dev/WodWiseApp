package com.migueldev.wodwiseapp.data.remote.datasource.login

import android.security.keystore.UserNotAuthenticatedException
import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.data.remote.service.AuthService
import com.migueldev.wodwiseapp.domain.exception.LoginFailedException
import javax.inject.Inject
import kotlinx.coroutines.tasks.await

class RemoteLoginDatasource @Inject constructor(
    private val authService: AuthService,
) : LoginDatasource {

    override suspend fun generateFirebaseUser(
        email: String,
        password: String,
    ): Either<Throwable, FirebaseUser> {
        return Either.catch {
            val user = authService.login(email, password)
            user ?: throw LoginFailedException()
        }
    }

    override suspend fun sendPasswordResetEmail(email: String): Either<Throwable, Unit> {
        return Either.catch {
            authService.sendPasswordResetEmail(email)
        }
    }

    override suspend fun deleteUser(): Either<Throwable, Unit> {
        return Either.catch {
            val currentUser = authService.getCurrentUser()
            currentUser?.delete()?.await()
                ?: throw UserNotAuthenticatedException()
        }
    }
}
