package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachFormatContent(
    coachState: CoachState,
    onPreviousFormatClicked: () -> Unit,
    onNextFormatClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.padding(Dimension.d8),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = coachState.formatTitleText, fontSize = 16.sp)
        CoachFormatSelector(
            coachState = coachState,
            onPreviousFormatClicked = onPreviousFormatClicked,
            onNextFormatClicked = onNextFormatClicked
        )
    }
}