package com.migueldev.wodwiseapp.presentation.screen.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.platform.LocalContext
import com.migueldev.wodwiseapp.presentation.navigation.AppButtonsState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.workout.composables.WorkoutDatePicker
import com.migueldev.wodwiseapp.presentation.screen.workout.composables.WorkoutDropdownMenuGroup
import com.migueldev.wodwiseapp.presentation.screen.workout.composables.WorkoutInstructionsTextField
import com.migueldev.wodwiseapp.presentation.screen.workout.composables.WorkoutRecorderButton
import com.migueldev.wodwiseapp.presentation.screen.workout.composables.WorkoutSaveButton
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutScreenContent(
    workoutViewModel: WorkoutViewModel,
    workoutState: WorkoutState,
    datePickerState: DatePickerState,
    appButtonsState: AppButtonsState,
) {
    LaunchedEffect(Unit) {
        workoutViewModel.resetCreateWorkoutState()
    }
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimension.d8)
    ) {
        if (workoutState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) { CircularProgressIndicator() }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .alpha(if (workoutState.isLoading) LOADING_ALPHA else DEFAULT_ALPHA)
        ) {
            WorkoutInstructionsTextField(
                workoutState = workoutState,
                value = workoutState.instructionsStateText,
                onTextChanged = { newText ->
                    workoutViewModel.updateInstructionsText(newText)
                }
            )
            Spacer(modifier = Modifier.height(Dimension.d8))
            WorkoutRecorderButton(
                workoutState = workoutState,
                appButtonsState = appButtonsState,
                context = context
            )
            Spacer(modifier = Modifier.height(Dimension.d8))
            WorkoutDropdownMenuGroup(
                workoutState = workoutState,
                workoutViewModel = workoutViewModel
            )
            Spacer(modifier = Modifier.height(Dimension.d8))
            WorkoutDatePicker(
                workoutState = workoutState,
                datePickerState = datePickerState,
                workoutViewModel = workoutViewModel
            )
            Spacer(modifier = Modifier.height(Dimension.d16))
        }
        WorkoutSaveButton(
            modifier = Modifier.align(Alignment.BottomCenter),
            workoutState = workoutState,
            appButtonsState = appButtonsState
        )
    }
}

private const val LOADING_ALPHA = 0.5f
private const val DEFAULT_ALPHA = 1f
