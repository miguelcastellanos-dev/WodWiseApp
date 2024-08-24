package com.migueldev.wodwiseapp.domain.usecase

import arrow.core.Either
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.core.verifyOnce
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test

class HandleResultIAUseCaseTest {

    private val resourceProvider = relaxedMockk<ResourceProvider>()

    private val handleResultIAUseCase = HandleResultIAUseCase(resourceProvider)

    @Test
    fun `GIVEN successful response WHEN handleResultIAUseCase THEN update state with response`() {
        val response = "Successful Response"
        val updateState: (String?) -> Unit = mockk(relaxed = true)

        handleResultIAUseCase(
            result = Either.Right(response),
            updateState = updateState
        )

        verifyOnce { updateState(response) }
    }

    @Test
    fun `GIVEN empty response WHEN handleResultIAUseCase THEN update state with resource message`() {
        val emptyResponse = ""
        val resourceMessage = "Resource Message"
        every { resourceProvider.getString(R.string.response_is_null_or_empty) } returns resourceMessage
        val updateState: (String?) -> Unit = mockk(relaxed = true)

        handleResultIAUseCase(
            result = Either.Right(emptyResponse),
            updateState = updateState
        )

        verifyOnce { updateState(resourceMessage) }
    }

    @Test
    fun `GIVEN error result WHEN handleResultIAUseCase THEN update state with error message`() {
        val errorMessage = "Error occurred"
        val updateState: (String?) -> Unit = mockk(relaxed = true)

        handleResultIAUseCase(
            result = Either.Left(Throwable(errorMessage)),
            updateState = updateState
        )

        verifyOnce { updateState(errorMessage) }
    }
}
