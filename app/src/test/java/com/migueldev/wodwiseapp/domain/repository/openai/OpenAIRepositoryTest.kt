package com.migueldev.wodwiseapp.domain.repository.openai

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import com.migueldev.wodwiseapp.data.remote.datasource.openai.OpenAIDatasource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OpenAIRepositoryTest {

    private val datasource = mockk<OpenAIDatasource>()

    private lateinit var repository: OpenAIRepository

    @BeforeEach
    fun setUp() {
        repository = OpenAIRepository(datasource)
    }

    @Test
    fun `GIVEN valid info and gpt model WHEN request THEN return response`(): Unit =
        runTest {
            val info = "Info"
            val gptModel = "Gpt model"
            val response = "Response"
            coEvery { datasource.requestChatCompletion(info, gptModel) } returns
                response.right()

            val result = repository.request(info, gptModel)

            result.isRight() shouldBe true
            response shouldBeEqualTo (result as Either.Right).value
            coEvery {
                datasource.requestChatCompletion(info, gptModel)
            }
        }

    @Test
    fun `GIVEN invalid information and model WHEN request THEN return Throwable`(): Unit =
        runTest {
            val info = "Info"
            val gptModel = "Gpt model"
            val exception = Exception("Exception")
            coEvery { datasource.requestChatCompletion(info, gptModel) } returns
                exception.left()

            val result = repository.request(info, gptModel)

            result.isLeft() shouldBe true
            exception shouldBeEqualTo (result as Either.Left).value
            coVerify {
                datasource.requestChatCompletion(info, gptModel)
            }
        }
}
