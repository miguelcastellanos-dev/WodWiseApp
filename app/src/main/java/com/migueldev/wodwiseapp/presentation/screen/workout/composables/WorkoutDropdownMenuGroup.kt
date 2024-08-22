package com.migueldev.wodwiseapp.presentation.screen.workout.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutViewModel
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

@Composable
fun WorkoutDropdownMenuGroup(
    workoutState: WorkoutState,
    workoutViewModel: WorkoutViewModel,
) {
    WorkoutDropdownMenu(
        items = workoutState.sessionItems.map { it.displayName },
        selectedValue = workoutState.sessionStateText.displayName,
        onValueChange = { newValue ->
            val sessionItem =
                workoutState.sessionItems.first { it.displayName == newValue }
            workoutViewModel.updateSelectedSession(sessionItem)
        }
    )
    Spacer(modifier = Modifier.height(Dimension.d8))
    WorkoutDropdownMenu(
        items = workoutState.positionSessionItems.map { it.displayName },
        selectedValue = workoutState.positionStateText.displayName,
        onValueChange = { newValue ->
            val positionItem =
                workoutState.positionSessionItems.first { it.displayName == newValue }
            workoutViewModel.updateSelectedPosition(positionItem)
        }
    )
    Spacer(modifier = Modifier.height(Dimension.d8))
    WorkoutDropdownMenu(
        items = workoutState.exerciseTypeItems.map { it.displayName },
        selectedValue = workoutState.exerciseTypeStateText.displayName,
        onValueChange = { newValue ->
            val exerciseTypeItem =
                workoutState.exerciseTypeItems.first { it.displayName == newValue }
            workoutViewModel.updateSelectedExerciseType(exerciseTypeItem)
        }
    )
}
