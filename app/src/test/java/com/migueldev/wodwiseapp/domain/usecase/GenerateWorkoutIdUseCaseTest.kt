package com.migueldev.wodwiseapp.domain.usecase

import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.providers.TimeProvider
import io.mockk.every
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GenerateWorkoutIdUseCaseTest {

    private val timeProvider = relaxedMockk<TimeProvider>()

    private lateinit var useCase: GenerateWorkoutIdUseCase

    @BeforeEach
    fun setUp() {
        useCase = GenerateWorkoutIdUseCase(timeProvider)
    }

    @Test
    fun `GIVEN currentTimeMillis WHEN invoke THEN return expected id as string`() {
        val expectedTime = 1627848392000L
        every { timeProvider.currentTimeMillis() } returns expectedTime

        val result = useCase()

        result shouldBeEqualTo expectedTime.toString()
    }

    @Test
    fun `GIVEN different currentTimeMillis WHEN invoke THEN return different id as string`() {
        val firstTime = 1627848392000L
        val secondTime = 1627848393000L
        every { timeProvider.currentTimeMillis() } returns firstTime andThen secondTime

        val firstResult = useCase()
        val secondResult = useCase()

        firstResult shouldBeEqualTo firstTime.toString()
        secondResult shouldBeEqualTo secondTime.toString()
    }
}
