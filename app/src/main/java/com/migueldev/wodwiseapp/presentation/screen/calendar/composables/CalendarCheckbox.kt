package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData

@Composable
fun CalendarCheckbox(
    workoutCardData: WorkoutCardData,
    modifier: Modifier,
    onCheckboxClicked: (String, Boolean) -> Unit,
) {
    Checkbox(
        modifier = modifier,
        checked = workoutCardData.checkboxState,
        onCheckedChange = { newState ->
            onCheckboxClicked(workoutCardData.workoutId, newState)
        },
        enabled = true,
        colors = CheckboxDefaults.colors(
            checkedColor = MaterialTheme.colorScheme.primary,
            uncheckedColor = MaterialTheme.colorScheme.primary,
            checkmarkColor = MaterialTheme.colorScheme.onPrimary
        )
    )
}
