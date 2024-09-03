package com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDetailNotesCard(
    calendarState: CalendarState,
    workoutCardData: WorkoutCardData,
    onNotesIconClicked: (String, String) -> Unit,
) {
    var localNotes by remember {
        mutableStateOf(
            calendarState.notesText[workoutCardData.workoutId] ?: workoutCardData.notes
        )
    }
    val isFocusedNotes = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(Dimension.d4)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocusedNotes.value = focusState.isFocused
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(Dimension.d8),
        border = BorderStroke(
            Dimension.d1,
            if (isFocusedNotes.value) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        )
    ) {
        CalendarDetailNotesIconButton(
            calendarState = calendarState,
            workoutCardData = workoutCardData,
            localNotes = localNotes,
            onNotesIconClicked = onNotesIconClicked
        )

        CalendarDetailNotesTextField(
            localNotes = localNotes,
            onValueChange = { newNotes ->
                localNotes = newNotes
            }
        )
    }
}
