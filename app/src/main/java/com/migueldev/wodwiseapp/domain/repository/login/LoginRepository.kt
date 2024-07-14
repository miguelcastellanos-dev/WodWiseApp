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
}
