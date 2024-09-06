package com.migueldev.wodwiseapp.presentation.screen.calendardetail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables.CalendarDetailCardView
import com.migueldev.wodwiseapp.presentation.screen.calendardetail.composables.CalendarDetailHeader
import com.migueldev.wodwiseapp.presentation.screen.calendardetail.utils.decodeAndSanitizeString
import com.migueldev.wodwiseapp.presentation.screen.calendardetail.utils.encodeSpecialString
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDetailScreen(
    appState: AppState,
    workoutCardData: WorkoutCardData,
    onCheckboxClicked: (String, Boolean) -> Unit,
    onInstructionIconClicked: (String, String) -> Unit,
    onNotesIconClicked: (String, String) -> Unit,
) {
    val focusManager = LocalFocusManager.current
    val checkboxState by appState.calendarViewModel.getCheckboxState(workoutCardData.workoutId)
        .collectAsState()
    val decodedInstructions = remember {
        decodeAndSanitizeString(workoutCardData.instructions) { str ->
            encodeSpecialString(str)
        }
    }
    val decodedNotes = remember {
        decodeAndSanitizeString(workoutCardData.notes) { str ->
            encodeSpecialString(str)
        }
    }

    Box(
        modifier = Modifier
            .clickable { focusManager.clearFocus() }
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.padding(Dimension.d4),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.secondaryContainer)
        ) {
            CalendarDetailHeader(workoutCardData)
            CalendarDetailCardView(
                calendarState = appState.calendarState,
                onCheckboxClicked = onCheckboxClicked,
                workoutCardData = WorkoutCardData(
                    workoutId = workoutCardData.workoutId,
                    date = workoutCardData.date,
                    session = workoutCardData.session,
                    positionSession = workoutCardData.positionSession,
                    exerciseType = workoutCardData.exerciseType,
                    instructions = decodedInstructions,
                    checkboxState = checkboxState,
                    notes = decodedNotes
                ),
                onInstructionIconClicked = onInstructionIconClicked,
                onNotesIconClicked = onNotesIconClicked

            )
            Spacer(modifier = Modifier.padding(Dimension.d144))
        }
    }
}
