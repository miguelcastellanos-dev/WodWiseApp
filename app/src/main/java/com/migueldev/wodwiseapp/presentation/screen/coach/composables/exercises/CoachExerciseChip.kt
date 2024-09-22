package com.migueldev.wodwiseapp.presentation.screen.coach.composables.exercises

import androidx.compose.material3.InputChip
import androidx.compose.material3.InputChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState

@Composable
fun CoachExerciseChip(
    coachState: CoachState,
    coachExerciseData: CoachExerciseData,
    onExerciseSelected: (String, Boolean) -> Unit,
) {
    val isSelected = remember { mutableStateOf(coachExerciseData.isSelected) }

    val contentDescription = if (isSelected.value) {
        "${coachExerciseData.exerciseName}, ${coachState.exerciseChipSelected}"
    } else {
        "${coachExerciseData.exerciseName}, ${coachState.exerciseChipNotSelected}"
    }

    InputChip(
        modifier = Modifier.semantics {
            this.contentDescription = contentDescription
        },
        selected = isSelected.value,
        onClick = {
            isSelected.value = !isSelected.value
            onExerciseSelected(coachExerciseData.exerciseName, isSelected.value)
        },
        label = {
            Text(
                text = coachExerciseData.exerciseName,
                fontSize = 12.sp
            )
        },
        colors = InputChipDefaults.inputChipColors(
            containerColor = MaterialTheme.colorScheme.background,
            labelColor = MaterialTheme.colorScheme.onBackground,
            selectedContainerColor = MaterialTheme.colorScheme.primary,
            selectedLabelColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
