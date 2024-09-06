package com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDetailInstructionsCard(
    calendarState: CalendarState,
    workoutCardData: WorkoutCardData,
    onInstructionIconClicked: (String, String) -> Unit,
) {
    var localInstructions by remember {
        mutableStateOf(
            calendarState.instructionsText[workoutCardData.workoutId]
                ?: workoutCardData.instructions
        )
    }
    val isFocusedInstructions = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(Dimension.d4)
            .fillMaxWidth()
            .onFocusChanged { focusState ->
                isFocusedInstructions.value = focusState.isFocused
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(Dimension.d8),
        border = BorderStroke(
            Dimension.d1,
            if (isFocusedInstructions.value) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        )
    ) {
        CalendarDetailInstructionsIconButton(
            calendarState = calendarState,
            workoutCardData = workoutCardData,
            localInstructions = localInstructions,
            onInstructionIconClicked = onInstructionIconClicked
        )

        CalendarDetailInstructionsTextField(
            localInstructions = localInstructions,
            onValueChange = { newInstructions ->
                localInstructions = newInstructions
            }
        )
    }
}
