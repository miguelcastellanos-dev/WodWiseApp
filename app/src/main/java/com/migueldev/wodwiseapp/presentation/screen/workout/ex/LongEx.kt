package com.migueldev.wodwiseapp.presentation.screen.workout.ex

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long?.millisToDate(): String {
    val dateFormat = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.getDefault())
    val dateToFormat = this?.let { Date(it) } ?: Date()

    return dateFormat.format(dateToFormat)
}

const val DATE_FORMAT_PATTERN = "EEEE dd MMMM"
