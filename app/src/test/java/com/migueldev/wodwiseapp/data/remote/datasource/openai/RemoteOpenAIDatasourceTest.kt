package com.migueldev.wodwiseapp.data.remote.datasource.openai

import com.aallam.openai.api.chat.ChatChoice
import com.aallam.openai.api.chat.ChatCompletion
import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.migueldev.wodwiseapp.domain.exception.OpenAIResponseNullException
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RemoteOpenAIDatasourceTest {

    private val openAI = mockk<OpenAI>()

    private lateinit var datasource: RemoteOpenAIDatasource

    @BeforeEach
    fun setUp() {
        datasource = RemoteOpenAIDatasource(openAI)
    }

    @Test
    fun `GIVEN valid info and gpt model WHEN requestChatCompletion THEN return response`() = runTest {
        val userInfo = "Info"
        val systemInfo = "System Info"
        val gptModel = "gpt-3.5-turbo"
        val response = "Response"
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(gptModel),
            messages = listOf(
                ChatMessage(role = ChatRole.System, content = systemInfo),
                ChatMessage(role = ChatRole.User, content = userInfo)
            )
        )
        val successResponse = buildSuccessResponse(response, gptModel)
        coEvery { openAI.chatCompletion(any()) } returns successResponse

        val result = datasource.requestChatCompletion(userInfo, systemInfo)

        result.isRight() shouldBeEqualTo true
        result.orNull() shouldBeEqualTo response
        coVerify {
            openAI.chatCompletion(
                withArg {
                    it.model shouldBeEqualTo chatCompletionRequest.model
                    it.messages.first().content shouldBeEqualTo chatCompletionRequest.messages.first().content
                    it.messages.last().content shouldBeEqualTo chatCompletionRequest.messages.last().content
                }
            )
        }
    }

    @Test
    fun `GIVEN invalid request WHEN requestChatCompletion THEN throw exception`() = runTest {
        val userInfo = "Info"
        val systemInfo = "System Info"
        val gptModel = "gpt-3.5-turbo"
        val chatCompletionRequest = ChatCompletionRequest(
            model = ModelId(gptModel),
            messages = listOf(
                ChatMessage(role = ChatRole.System, content = systemInfo),
                ChatMessage(role = ChatRole.User, content = userInfo)
            )
        )
        val exception = OpenAIResponseNullException()
        coEvery { openAI.chatCompletion(any()) } throws exception

        val result = datasource.requestChatCompletion(userInfo, systemInfo)

        result.isLeft() shouldBeEqualTo true
        result.swap().orNull() shouldBeEqualTo exception
        coVerify {
            openAI.chatCompletion(
                withArg {
                    it.model shouldBeEqualTo chatCompletionRequest.model
                    it.messages.first().content shouldBeEqualTo chatCompletionRequest.messages.first().content
                    it.messages.last().content shouldBeEqualTo chatCompletionRequest.messages.last().content
                }
            )
        }
    }

    private fun buildSuccessResponse(response: String, gptModel: String): ChatCompletion {
        return ChatCompletion(
            id = "test-id",
            created = 1234567890L,
            model = ModelId(gptModel),
            choices = listOf(
                ChatChoice(
                    index = 0,
                    message = ChatMessage(
                        role = ChatRole.Assistant,
                        content = response
                    )
                )
            )
        )
    }
}
