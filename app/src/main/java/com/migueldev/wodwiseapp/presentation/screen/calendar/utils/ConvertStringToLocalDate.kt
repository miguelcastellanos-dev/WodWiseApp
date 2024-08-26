package com.migueldev.wodwiseapp.presentation.screen.calendar.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.migueldev.wodwiseapp.domain.exception.ConvertStringToLocalDateException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

@RequiresApi(Build.VERSION_CODES.O)
fun convertStringToLocalDate(dateString: String, pattern: String): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern)
        val currentYear = LocalDate.now().year
        val dateWithYear = "$dateString $currentYear"
        LocalDate.parse(dateWithYear, formatter)
    } catch (e: DateTimeParseException) {
        throw ConvertStringToLocalDateException(e)
    }
}
