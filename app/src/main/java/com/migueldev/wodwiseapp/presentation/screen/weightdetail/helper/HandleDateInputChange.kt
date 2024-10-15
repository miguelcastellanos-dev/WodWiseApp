package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

fun handleDateInputChange(input: String, onDateChange: (String) -> Unit): String {
    if (input.length <= MAX_DATE_LENGTH && input.all { it.isDigit() }) {
        val formattedDate = buildString {
            if (input.length >= DAY_MONTH_LENGTH) {
                append(input.substring(0, DAY_MONTH_LENGTH))
            } else {
                append(input)
            }
            if (input.length >= MONTH_YEAR_LENGTH) {
                append("/").append(input.substring(DAY_MONTH_LENGTH, MONTH_YEAR_LENGTH))
            }
            if (input.length == MAX_DATE_LENGTH) {
                append("/").append(input.substring(MONTH_YEAR_LENGTH, MAX_DATE_LENGTH))
            }
        }
        onDateChange(formattedDate)
        return input
    }
    return ""
}

private const val MAX_DATE_LENGTH = 8
private const val DAY_MONTH_LENGTH = 2
private const val MONTH_YEAR_LENGTH = 4
