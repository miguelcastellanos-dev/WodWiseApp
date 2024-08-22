package com.migueldev.wodwiseapp.domain.usecase

import arrow.core.Either
import com.migueldev.wodwiseapp.domain.repository.openai.OpenAIRepository
import javax.inject.Inject

class RequestChatCompletionIAUseCase @Inject constructor(
    private val openAIRepository: OpenAIRepository,
) {
    suspend operator fun invoke(
        userInfo: String,
        systemInfo: String,
    ): Either<Throwable, String> {
        return openAIRepository.request(userInfo, systemInfo)
    }
}
