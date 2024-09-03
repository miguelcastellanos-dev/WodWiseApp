package com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDetailNotesIconButton(
    calendarState: CalendarState,
    workoutCardData: WorkoutCardData,
    localNotes: String,
    onNotesIconClicked: (String, String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val originalNotes = calendarState.notesText[workoutCardData.workoutId] ?: workoutCardData.notes
    val isSaveEnabledNotes = localNotes != originalNotes

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .padding(Dimension.d8)
                .weight(1f),
            text = calendarState.notesTextTitleInCardView,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
        )

        IconButton(
            onClick = {
                onNotesIconClicked(workoutCardData.workoutId, localNotes)
                focusManager.clearFocus()
            },
            enabled = isSaveEnabledNotes
        ) {
            Icon(
                imageVector = Icons.Default.Save,
                contentDescription = "Save Notes",
                tint = if (isSaveEnabledNotes) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
        }
    }
}