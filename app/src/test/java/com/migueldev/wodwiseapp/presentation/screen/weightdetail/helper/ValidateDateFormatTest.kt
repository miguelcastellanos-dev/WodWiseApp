package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class ValidateDateFormatTest {

    @Test
    fun `GIVEN valid date WHEN validateDateFormat is called THEN return true`() {
        val validDate = "15/10/2023"

        val result = validateDateFormat(validDate)

        result shouldBeEqualTo true
    }

    @Test
    fun `GIVEN invalid format date WHEN validateDateFormat is called THEN return false`() {
        val invalidDate = "15-10-2023"

        val result = validateDateFormat(invalidDate)

        result shouldBeEqualTo false
    }

    @Test
    fun `GIVEN date with invalid day WHEN validateDateFormat is called THEN return false`() {
        val invalidDay = "32/10/2023"

        val result = validateDateFormat(invalidDay)

        result shouldBeEqualTo false
    }

    @Test
    fun `GIVEN date with invalid month WHEN validateDateFormat is called THEN return false`() {
        // Arrange
        val invalidMonth = "12/32/2023"

        // Act
        val result = validateDateFormat(invalidMonth)

        // Assert
        result shouldBeEqualTo false
    }

    @Test
    fun `GIVEN date with invalid year WHEN validateDateFormat is called THEN return false`() {
        val invalidYear = "15/10/3000"

        val result = validateDateFormat(invalidYear)

        result shouldBeEqualTo false
    }

    @Test
    fun `GIVEN empty date WHEN validateDateFormat is called THEN return false`() {
        val emptyDate = ""

        val result = validateDateFormat(emptyDate)

        result shouldBeEqualTo false
    }

    @Test
    fun `GIVEN date with non-numeric characters WHEN validateDateFormat is called THEN return false`() {
        val invalidDate = "1a/10/2023"

        val result = validateDateFormat(invalidDate)

        result shouldBeEqualTo false
    }

    @Test
    fun `GIVEN valid date at boundaries WHEN validateDateFormat is called THEN return true`() {
        val boundaryDate = "01/01/1900"

        val result = validateDateFormat(boundaryDate)

        result shouldBeEqualTo true
    }

    @Test
    fun `GIVEN another valid date at boundaries WHEN validateDateFormat is called THEN return true`() {
        val boundaryDate = "31/31/2500"

        val result = validateDateFormat(boundaryDate)

        result shouldBeEqualTo true
    }
}
