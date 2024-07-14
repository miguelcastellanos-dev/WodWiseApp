package com.migueldev.wodwiseapp.data.remote.datasource.login

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser

interface LoginDatasource {

    suspend fun generateFirebaseUser(
        email: String,
        password: String,
    ): Either<Throwable, FirebaseUser>
}
