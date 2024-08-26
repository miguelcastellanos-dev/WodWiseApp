package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachFormatAndTimeCard(
    coachState: CoachState,
    coachActionState: CoachActionState,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        border = BorderStroke(
            Dimension.d1,
            MaterialTheme.colorScheme.secondaryContainer
        )
    ) {
        CoachFormatContent(
            coachState = coachState,
            onPreviousFormatClicked = coachActionState.onPreviousFormatClicked,
            onNextFormatClicked = coachActionState.onNextFormatClicked
        )
        CoachTimeContent(
            coachState = coachState,
            onSelectedTimeChange = coachActionState.onSelectedTimeChange
        )
    }
}
