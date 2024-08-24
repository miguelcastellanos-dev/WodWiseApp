package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarCardView(
    appState: AppState,
    appActionState: AppActionState,
    workoutCardData: WorkoutCardData,
) {
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(1f)) {
                CalendarDeleteIconButton(
                    modifier = Modifier.align(Alignment.TopStart),
                    workoutId = workoutCardData.workoutId,
                    onDeleteIconClicked = appActionState.onDeleteIconClicked
                )
            }
            Box(modifier = Modifier.weight(1f)) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
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
                    onCheckboxClicked = appActionState.onCheckboxClicked
                )
            }
        }
        Column(modifier = Modifier.padding(Dimension.d8)) {
            CalendarCardViewTexts(
                calendarState = appState.calendarState,
                workoutCardData = workoutCardData
            )
        }
    }
}
