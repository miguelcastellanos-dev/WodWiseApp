package com.migueldev.wodwiseapp.presentation.screen.workout.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutViewModel
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState
import com.migueldev.wodwiseapp.presentation.screen.workout.ex.millisToDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WorkoutDatePicker(
    workoutState: WorkoutState,
    datePickerState: DatePickerState,
    modifier: Modifier = Modifier,
    workoutViewModel: WorkoutViewModel,
) {
    var selectedDate by remember {
        mutableStateOf(
            workoutState.selectDateTitleText
        )
    }
    LaunchedEffect(datePickerState.selectedDateMillis) {
        selectedDate = datePickerState.selectedDateMillis.millisToDate()
        if (datePickerState.selectedDateMillis != null) {
            workoutViewModel.updateIsDatePickerVisible(false)
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WorkoutDatePickerButton(
            workoutState = workoutState,
            isDatePickerVisible = workoutState.isDatePickerVisible,
            selectedDate = selectedDate,
            onClick = {
                workoutViewModel.updateIsDatePickerVisible(
                    !workoutState.isDatePickerVisible
                )
            },
            modifier = modifier
        )
        Spacer(modifier = modifier.height(Dimension.d8))
        if (workoutState.isDatePickerVisible) {
            DatePicker(
                headline = null,
                title = null,
                state = datePickerState,
                showModeToggle = false,
                modifier = modifier.fillMaxWidth()
            )
        }
    }
}
