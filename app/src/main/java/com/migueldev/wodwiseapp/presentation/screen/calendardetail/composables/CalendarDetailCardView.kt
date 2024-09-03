package com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.calendar.composables.CalendarCheckbox
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDetailCardView(
    calendarState: CalendarState,
    workoutCardData: WorkoutCardData,
    onCheckboxClicked: (String, Boolean) -> Unit,
    onInstructionIconClicked: (String, String) -> Unit,
    onNotesIconClicked: (String, String) -> Unit,
) {
    Column {
        Card(
            border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.secondaryContainer),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.d4),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            shape = RoundedCornerShape(Dimension.d8)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    Text(
                        modifier = Modifier.align(Alignment.TopEnd),
                        text = workoutCardData.positionSession,
                        style = TextStyle(
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    CalendarCheckbox(
                        workoutCardData = workoutCardData,
                        modifier = Modifier.align(Alignment.TopEnd),
                        onCheckboxClicked = onCheckboxClicked
                    )
                }
            }
            Text(
                modifier = Modifier
                    .padding(Dimension.d8),
                text = "${workoutCardData.exerciseType}:",
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 24.sp
                ),
                color = MaterialTheme.colorScheme.onBackground
            )
        }

        CalendarDetailInstructionsCard(
            calendarState = calendarState,
            workoutCardData = workoutCardData,
            onInstructionIconClicked = onInstructionIconClicked
        )
        CalendarDetailNotesCard(
            calendarState = calendarState,
            workoutCardData = workoutCardData,
            onNotesIconClicked = onNotesIconClicked
        )
    }
}
