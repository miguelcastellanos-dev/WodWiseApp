package com.migueldev.wodwiseapp.presentation.screen.coach.composables.exercises

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachViewModel
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachExerciseCard(
    coachState: CoachState,
    coachViewModel: CoachViewModel,
    coachExerciseData: List<CoachExerciseData>,
    onExerciseSelected: (String, Boolean) -> Unit,
    exerciseHeaderText: String,
) {
    val showDayCardState = coachState.showExerciseCardState[exerciseHeaderText] == true
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                coachViewModel.foldOrUnfoldExerciseTab(exerciseHeaderText)
            },
        colors = CardDefaults.cardColors(
            containerColor =
            if (showDayCardState) {
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
        CoachExerciseHeader(exerciseHeaderText)
        if (coachState.showExerciseCardState[exerciseHeaderText] == true) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(COLUMNS_NUMBER),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = Dimension.d0, max = Dimension.d480),
                contentPadding = PaddingValues(Dimension.d8),
                verticalArrangement = Arrangement.spacedBy(Dimension.d8),
                horizontalArrangement = Arrangement.spacedBy(Dimension.d8)
            ) {
                items(coachExerciseData) { exerciseState ->
                    CoachExerciseChip(exerciseState, onExerciseSelected)
                }
            }
        }
    }
}

private const val COLUMNS_NUMBER = 2
