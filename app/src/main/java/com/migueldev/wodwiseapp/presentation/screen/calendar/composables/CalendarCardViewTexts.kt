package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarCardViewTexts(
    calendarState: CalendarState,
    workoutCardData: WorkoutCardData,
) {
    Text(
        modifier = Modifier
            .padding(bottom = Dimension.d8),
        text = "${workoutCardData.exerciseType}:",
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        ),
        color = MaterialTheme.colorScheme.onBackground
    )
    Text(
        modifier = Modifier
            .padding(bottom = Dimension.d8),
        text = calendarState.instructionTextTitleInCardView,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    )
    SelectionContainer {
        Text(
            modifier = Modifier
                .padding(bottom = Dimension.d8),
            text = workoutCardData.instructions,
            style = TextStyle(
                fontSize = 16.sp
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Spacer(modifier = Modifier.padding(Dimension.d8))
    Text(
        modifier = Modifier
            .padding(bottom = Dimension.d8),
        text = calendarState.notesTextTitleInCardView,
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp
        )
    )
    Text(
        modifier = Modifier
            .padding(bottom = Dimension.d8),
        text = workoutCardData.notes,
        style = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily.Cursive
        ),
        color = MaterialTheme.colorScheme.onBackground
    )
}
