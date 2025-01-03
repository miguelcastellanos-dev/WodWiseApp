package com.migueldev.wodwiseapp.presentation.screen.calendar.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.migueldev.wodwiseapp.domain.exception.ConvertStringToLocalDateException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
fun convertStringToLocalDate(dateString: String): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(STRING_TO_LOCAL_DATE_PATTERN)

        val finalDateString = dateString.substringAfter(' ')

        LocalDate.parse(finalDateString, formatter)
    } catch (e: DateTimeParseException) {
        throw ConvertStringToLocalDateException()
    }
}

private const val STRING_TO_LOCAL_DATE_PATTERN = "dd MMMM yyyy"
