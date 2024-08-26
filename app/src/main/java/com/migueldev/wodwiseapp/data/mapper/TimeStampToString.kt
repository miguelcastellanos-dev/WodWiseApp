package com.migueldev.wodwiseapp.data.mapper

import arrow.core.Either
import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.domain.exception.TimeStampFirestoreException
import com.migueldev.wodwiseapp.presentation.screen.workout.ex.DATE_FORMAT_PATTERN
import java.text.SimpleDateFormat

fun timeStampToString(
    timestamp: Timestamp,
    localeProvider: LocaleProvider = DefaultLocaleProvider(),
): Either<TimeStampFirestoreException, String> {
    return Either.catch {
        val date = timestamp.toDate()
        val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN, localeProvider.getLocale())
        dateFormat.format(date)
    }.mapLeft { error ->
        TimeStampFirestoreException(error)
    }
}
