package com.migueldev.wodwiseapp.data.mapper

import arrow.core.Either
import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.domain.exception.TimeStampFirestoreException
import com.migueldev.wodwiseapp.presentation.screen.workout.ex.DATE_FORMAT_PATTERN
import io.mockk.every
import io.mockk.mockk
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Locale
import org.amshove.kluent.shouldBe
import org.amshove.kluent.shouldBeEqualTo
import org.amshove.kluent.shouldBeInstanceOf
import org.junit.jupiter.api.Test

class TimeStampToStringTest {

    private val localeProvider = relaxedMockk<LocaleProvider>()

    @Test
    fun `GIVEN timestamp WHEN timeStampToString called THEN return correct formatted string`() {
        val timestamp = Timestamp(seconds = 1721772240, nanoseconds = 615000000)
        val expectedLocale = Locale("en", "US")
        val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN, expectedLocale)
        val expectedDateString = dateFormat.format(timestamp.toDate())
        every { localeProvider.getLocale() } returns expectedLocale

        val result = timeStampToString(timestamp, localeProvider)

        result.isRight() shouldBe true
        result.orNull() shouldBeEqualTo expectedDateString
    }

    @Test
    fun `GIVEN invalid date format WHEN timeStampToString called THEN return TimeStampFirestoreException`() {
        val validTimestamp = Timestamp(seconds = 1721772240, nanoseconds = 615000000)
        val expectedLocale = Locale("en", "US")
        every { localeProvider.getLocale() } returns expectedLocale
        val brokenDateFormat = mockk<SimpleDateFormat>()
        every { brokenDateFormat.format(any()) } throws ParseException("Invalid format", 0)

        val result = Either.catch {
            brokenDateFormat.format(validTimestamp.toDate())
        }.mapLeft { error ->
            TimeStampFirestoreException(error)
        }

        result.isLeft() shouldBe true
        result.fold(
            { error -> error.shouldBeInstanceOf<TimeStampFirestoreException>() },
            { throw AssertionError("Expected Left, but got Right") }
        )
    }
}
