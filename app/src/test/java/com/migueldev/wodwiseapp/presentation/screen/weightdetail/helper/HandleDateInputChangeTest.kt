package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

import com.migueldev.wodwiseapp.core.verifyNever
import com.migueldev.wodwiseapp.core.verifyOnce
import io.mockk.mockk
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HandleDateInputChangeTest {

    private lateinit var onDateChangeMock: (String) -> Unit

    @BeforeEach
    fun setUp() {
        onDateChangeMock = mockk(relaxed = true)
    }

    @Test
    fun `GIVEN valid input WHEN handleDateInputChange is called THEN format date correctly`() {
        val input = "01012024"

        val result = handleDateInputChange(input, onDateChangeMock)

        result shouldBeEqualTo input
        verifyOnce { onDateChangeMock("01/01/2024") }
    }

    @Test
    fun `GIVEN input with less than max length WHEN handleDateInputChange is called THEN format partial date`() {
        val input = "0101"

        val result = handleDateInputChange(input, onDateChangeMock)

        result shouldBeEqualTo input
        verifyOnce { onDateChangeMock("01/01") }
    }

    @Test
    fun `GIVEN input with less than day-month length WHEN handleDateInputChange is called THEN return input as is`() {
        val input = "01"

        val result = handleDateInputChange(input, onDateChangeMock)

        result shouldBeEqualTo input
        verifyOnce { onDateChangeMock("01") }
    }

    @Test
    fun `GIVEN input exceeds max length WHEN handleDateInputChange is called THEN return empty string`() {
        val input = "010120240"

        val result = handleDateInputChange(input, onDateChangeMock)

        result shouldBeEqualTo ""
        verifyNever { onDateChangeMock(any()) }
    }

    @Test
    fun `GIVEN input with non-numeric characters WHEN handleDateInputChange is called THEN return empty string`() {
        val input = "01a12024"

        val result = handleDateInputChange(input, onDateChangeMock)

        result shouldBeEqualTo ""
        verifyNever { onDateChangeMock(any()) }
    }
}
