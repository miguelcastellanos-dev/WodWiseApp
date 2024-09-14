package com.migueldev.wodwiseapp.domain.repository.login

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.data.remote.datasource.login.LoginDatasource
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val loginDatasource: LoginDatasource,
) {
    suspend fun login(email: String, password: String): Either<Throwable, FirebaseUser> {
        return loginDatasource.generateFirebaseUser(email, password)
    }

    suspend fun sendPasswordResetEmail(email: String): Either<Throwable, Unit> {
        return Either.catch {
            loginDatasource.sendPasswordResetEmail(email)
        }
    }

    suspend fun deleteUser(): Either<Throwable, Unit> {
        return loginDatasource.deleteUser()
    }
}
