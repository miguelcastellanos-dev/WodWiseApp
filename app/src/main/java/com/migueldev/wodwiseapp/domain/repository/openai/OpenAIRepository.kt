package com.migueldev.wodwiseapp.domain.repository.openai

import arrow.core.Either
import com.migueldev.wodwiseapp.data.remote.datasource.openai.OpenAIDatasource
import javax.inject.Inject

class OpenAIRepository @Inject constructor(
    private val openAIDatasource: OpenAIDatasource,
) {

    suspend fun request(userInfo: String, systemInfo: String): Either<Throwable, String> {
        return openAIDatasource.requestChatCompletion(userInfo, systemInfo)
    }
}
