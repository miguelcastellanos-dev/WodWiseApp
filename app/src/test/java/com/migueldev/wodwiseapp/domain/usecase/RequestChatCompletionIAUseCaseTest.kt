package com.migueldev.wodwiseapp.domain.usecase

import arrow.core.Either
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.repository.openai.OpenAIRepository
import io.mockk.coEvery
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class RequestChatCompletionIAUseCaseTest {

    private val openAIRepository = relaxedMockk<OpenAIRepository>()
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()

    private val useCase = RequestChatCompletionIAUseCase(openAIRepository)

    @Test
    fun `GIVEN successful repository request WHEN useCase is invoked THEN it should return expected response`() =
        runTest(testDispatcher) {
            val userInfo = "infoUser"
            val systemInfo = "systemInfo"
            val expectedResponse = "Expected Response"
            coEvery { openAIRepository.request(userInfo, systemInfo) } returns Either.Right(expectedResponse)

            val result = useCase(userInfo, systemInfo)
            result shouldBeEqualTo Either.Right(expectedResponse)

            coVerifyOnce { openAIRepository.request(userInfo, systemInfo) }
        }

    @Test
    fun `GIVEN failed repository request WHEN useCase is invoked THEN it should return error`() =
        runTest(testDispatcher) {
            val userInfo = "infoUser"
            val systemInfo = "systemInfo"
            val expectedError = Throwable("Some error")
            coEvery { openAIRepository.request(userInfo, systemInfo) } returns Either.Left(expectedError)

            val result = useCase(userInfo, systemInfo)
            result shouldBeEqualTo Either.Left(expectedError)

            coVerifyOnce { openAIRepository.request(userInfo, systemInfo) }
        }
}
