package com.migueldev.wodwiseapp.presentation.screen.coach.composables.exercises

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SelectedExercisesCards(
    coachState: CoachState,
) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        coachState.selectedExerciseLists.forEach { exercise ->
            Card(
                modifier = Modifier.padding(Dimension.d4),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                border = BorderStroke(
                    Dimension.d1,
                    MaterialTheme.colorScheme.secondaryContainer
                )
            ) {
                Text(
                    modifier = Modifier.padding(Dimension.d4),
                    text = exercise,
                    fontSize = 12.sp
                )
            }
        }
    }
}
