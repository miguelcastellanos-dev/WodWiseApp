package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.calendar.utils.groupAndSortWorkoutsBySession
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarCardsSortedBySession(
    appState: AppState,
    appActionState: AppActionState,
    workoutsList: List<WorkoutCardData>,
    showDayCardState: Boolean,
) {
    val groupedWorkoutsBySession = groupAndSortWorkoutsBySession(workoutsList)

    if (showDayCardState) {
        groupedWorkoutsBySession.keys.sorted().forEach { session ->
            val workoutsInSession = groupedWorkoutsBySession[session] ?: emptyList()
            Card(
                modifier = Modifier
                    .padding(
                        top = Dimension.d4,
                        start = Dimension.d4,
                        end = Dimension.d4,
                        bottom = Dimension.d4
                    ),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                ),
                border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.secondaryContainer)
            ) {
                Text(
                    text = session,
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    ),
                    modifier = Modifier
                        .padding(Dimension.d16)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    textAlign = TextAlign.Center
                )
                workoutsInSession.forEach { workoutCardData ->
                    CalendarCardViewContent(
                        appState = appState,
                        appActionState = appActionState,
                        workoutCardData = workoutCardData,
                        isCardClickable = true
                    )
                }
            }
        }
    }
}
