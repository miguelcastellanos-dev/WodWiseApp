package com.migueldev.wodwiseapp.presentation.screen.workout.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

@Composable
fun WorkoutDatePickerButton(
    workoutState: WorkoutState,
    isDatePickerVisible: Boolean,
    selectedDate: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Button(
        modifier = modifier
            .fillMaxWidth()
            .size(width = Dimension.d192, height = Dimension.d64),
        shape = RoundedCornerShape(topStart = Dimension.d4, topEnd = Dimension.d4),
        onClick = {
            keyboardController?.hide()
            focusManager.clearFocus()
            onClick()
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.secondaryContainer)
    ) {
        Text(
            text = if (isDatePickerVisible) {
                workoutState.selectDateTitleText
            } else {
                selectedDate
            },
            fontSize = 16.sp
        )
    }
}
