package com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.sp

@Composable
fun CalendarDetailNotesTextField(
    localNotes: String,
    onValueChange: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        modifier = Modifier
            .focusRequester(focusRequester),
        value = localNotes,
        onValueChange = { newNotes ->
            onValueChange(newNotes)
        },
        textStyle = TextStyle(
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedTextColor = MaterialTheme.colorScheme.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.onBackground,
            focusedPlaceholderColor = MaterialTheme.colorScheme.onBackground
        )
    )
}
