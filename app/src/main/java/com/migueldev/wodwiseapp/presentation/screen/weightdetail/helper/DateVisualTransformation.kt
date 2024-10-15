package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

class DateVisualTransformation : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        val trimmed = if (text.text.length >= MAX_DATE_LENGTH) {
            text.text.substring(0 until MAX_DATE_LENGTH)
        } else {
            text.text
        }

        var out = ""
        for (i in trimmed.indices) {
            out += trimmed[i]
            if (i == DAY_MONTH_SEPARATOR_INDEX || i == MONTH_YEAR_SEPARATOR_INDEX) out += "/"
        }

        val numberOffsetTranslator = object : OffsetMapping {
            override fun originalToTransformed(offset: Int): Int {
                return when {
                    offset <= DAY_MONTH_SEPARATOR_INDEX -> offset
                    offset <= MONTH_YEAR_SEPARATOR_INDEX -> offset + FIRST_SEPARATOR_OFFSET
                    offset <= MAX_DATE_LENGTH -> offset + SECOND_SEPARATOR_OFFSET
                    else -> MAX_TRANSFORMED_LENGTH
                }
            }

            override fun transformedToOriginal(offset: Int): Int {
                return when {
                    offset <= DAY_MONTH_SEPARATOR_INDEX + FIRST_SEPARATOR_OFFSET -> offset
                    offset <=
                        MONTH_YEAR_SEPARATOR_INDEX +
                        FIRST_SEPARATOR_OFFSET -> offset - FIRST_SEPARATOR_OFFSET

                    offset <= MAX_TRANSFORMED_LENGTH -> offset - SECOND_SEPARATOR_OFFSET
                    else -> MAX_ORIGINAL_LENGTH
                }
            }
        }

        return TransformedText(AnnotatedString(out), numberOffsetTranslator)
    }
}

private const val MAX_DATE_LENGTH = 8
private const val DAY_MONTH_SEPARATOR_INDEX = 1
private const val MONTH_YEAR_SEPARATOR_INDEX = 3
private const val FIRST_SEPARATOR_OFFSET = 1
private const val SECOND_SEPARATOR_OFFSET = 2
private const val MAX_TRANSFORMED_LENGTH = 10
private const val MAX_ORIGINAL_LENGTH = 8
