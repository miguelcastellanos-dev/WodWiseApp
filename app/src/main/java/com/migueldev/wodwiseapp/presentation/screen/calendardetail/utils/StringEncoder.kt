package com.migueldev.wodwiseapp.presentation.screen.calendardetail.utils

import java.net.URLDecoder
import java.nio.charset.StandardCharsets

fun decodeAndSanitizeString(
    instructions: String,
    encodeSpecialCharacters: (String) -> String,
): String {
    val sanitizedEncodedInstructions = encodeSpecialCharacters(instructions)
    return URLDecoder.decode(sanitizedEncodedInstructions, StandardCharsets.UTF_8.toString())
}

fun encodeSpecialString(encodedString: String): String {
    return encodedString
        .replace("\\n", "%0A")
        .replace("%", "%25")
}
