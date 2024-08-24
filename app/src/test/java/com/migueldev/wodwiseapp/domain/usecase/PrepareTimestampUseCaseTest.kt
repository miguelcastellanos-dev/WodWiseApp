package com.migueldev.wodwiseapp.domain.usecase

import com.google.firebase.Timestamp
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PrepareTimestampUseCaseTest {

    private lateinit var useCase: PrepareTimestampUseCase

    @BeforeEach
    fun setUp() {
        useCase = PrepareTimestampUseCase()
    }

    @Test
    fun `GIVEN valid date WHEN prepareTimestampUseCase is called THEN returns Timestamp with the same date`() {
        val validDate = 1623848400000L
        val validDateTimestamp = Timestamp(
            validDate / MILLISECONDS_IN_A_SECOND,
            ((validDate % MILLISECONDS_IN_A_SECOND) * NANOSECONDS_IN_A_MILLISECOND).toInt()
        )

        val result = useCase(validDate)

        result shouldBeEqualTo validDateTimestamp
    }
}
