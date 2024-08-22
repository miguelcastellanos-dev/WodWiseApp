package com.migueldev.wodwiseapp.domain.usecase

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ValidateInstructionsUseCaseTest {

    private lateinit var useCase: ValidateInstructionsUseCase

    @BeforeEach
    fun setUp() {
        useCase = ValidateInstructionsUseCase()
    }

    @Test
    fun `GIVEN empty instruction WHEN validateInstructions is called THEN returns string`() {
        val emptyInstruction = ""
        val stringReturn = "Empty workout"

        val result = useCase(emptyInstruction)

        result shouldBeEqualTo stringReturn
    }
}
