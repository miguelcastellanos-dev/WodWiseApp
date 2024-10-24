package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

fun validateDateFormat(date: String): Boolean {
    val parts = date.split("/").map { it.toIntOrNull() }
    val day = parts.getOrNull(0)
    val month = parts.getOrNull(1)
    val year = parts.getOrNull(2)

    return date.matches(Regex(DATE_REGEX_PATTERN)) &&
        day != null && month != null && year != null &&
        day in MIN_DAY..MAX_DAY &&
        month in MIN_MONTH..MAX_MONTH &&
        year in MIN_YEAR..MAX_YEAR
}

private const val DATE_REGEX_PATTERN = "^\\d{2}/\\d{2}/\\d{4}$"
private const val MIN_DAY = 1
private const val MAX_DAY = 31
private const val MIN_MONTH = 1
private const val MAX_MONTH = 31
private const val MIN_YEAR = 1900
private const val MAX_YEAR = 2500
