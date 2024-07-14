package com.migueldev.wodwiseapp.data.remote.datasource.signup

import arrow.core.Either
import com.google.firebase.auth.FirebaseUser

interface SignUpDatasource {

    suspend fun registerWithEmail(
        email: String,
        password: String,
    ): Either<Throwable, FirebaseUser>
}
