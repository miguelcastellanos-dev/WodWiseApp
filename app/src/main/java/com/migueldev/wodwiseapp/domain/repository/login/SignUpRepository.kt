package com.migueldev.wodwiseapp.domain.repository.login

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser
import com.migueldev.wodwiseapp.data.remote.datasource.signup.SignUpDatasource
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    private val signupDatasource: SignUpDatasource,
) {
    suspend fun register(email: String, password: String): Either<Throwable, FirebaseUser> {
        return signupDatasource.registerWithEmail(email, password)
    }
}
