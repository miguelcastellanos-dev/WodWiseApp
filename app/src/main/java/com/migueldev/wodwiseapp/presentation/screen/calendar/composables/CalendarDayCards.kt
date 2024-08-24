package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDayCards(
    appState: AppState,
    appActionState: AppActionState,
    date: String,
    workoutsList: List<WorkoutCardData>,
    showDayCardState: Boolean,
) {
    Card(
        modifier = Modifier.padding(
            start = Dimension.d8,
            bottom = Dimension.d8
        ),
        colors = CardDefaults.cardColors(
            containerColor = if (showDayCardState) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            } else {
                MaterialTheme.colorScheme.background
            }
        ),
        border = BorderStroke(
            Dimension.d1,
            if (showDayCardState) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        )
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        appState.calendarViewModel.foldOrUnfoldDayTab(date)
                    }
            ) {
                Text(
                    text = date,
                    modifier = Modifier
                        .padding(Dimension.d16),
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
            CalendarCardsSortedBySession(
                appState = appState,
                appActionState = appActionState,
                workoutsList = workoutsList,
                showDayCardState = showDayCardState
            )
        }
    }
}