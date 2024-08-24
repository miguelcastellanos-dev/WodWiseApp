package com.migueldev.wodwiseapp.data.remote.datasource.openai

import arrow.core.Either

interface OpenAIDatasource {

    suspend fun requestChatCompletion(
        userInfo: String,
        systemInfo: String,
    ): Either<Throwable, String>
}
