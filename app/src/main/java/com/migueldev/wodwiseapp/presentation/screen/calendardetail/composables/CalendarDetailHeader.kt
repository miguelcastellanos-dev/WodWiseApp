package com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDetailHeader(
    workout: WorkoutCardData,
) {
    Row {
        Text(
            text = workout.session,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier.padding(
                Dimension.d16,
                Dimension.d16,
                Dimension.d16,
                Dimension.d16
            ),
            color = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text = workout.date,
            style = TextStyle(
                fontSize = 16.sp
            ),
            modifier = Modifier
                .padding(
                    Dimension.d16,
                    Dimension.d16,
                    Dimension.d16,
                    Dimension.d16
                )
                .weight(1f),
            textAlign = TextAlign.End,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}
