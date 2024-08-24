package com.migueldev.wodwiseapp.data.remote.datasource.openai

import arrow.core.Either
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.migueldev.wodwiseapp.domain.exception.OpenAIResponseNullException
import javax.inject.Inject

class RemoteOpenAIDatasource @Inject constructor(
    private val openAI: OpenAI,
) : OpenAIDatasource {
    override suspend fun requestChatCompletion(
        userInfo: String,
        systemInfo: String,
    ): Either<Throwable, String> {
        return Either.catch {
            val chatCompletionRequest = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = systemInfo
                    ),
                    ChatMessage(
                        role = ChatRole.User,
                        content = userInfo
                    )
                )
            )
            val response = openAI
                .chatCompletion(chatCompletionRequest)
                .choices
                .firstOrNull()?.message?.content

            response?.takeIf { it.isNotEmpty() }
                ?: throw OpenAIResponseNullException()
        }
    }
}
