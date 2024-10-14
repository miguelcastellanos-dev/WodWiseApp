package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachButtonsRow(
    coachState: CoachState,
    onResetCoachScreenClicked: () -> Unit,
    onGenerateResponseClicked: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .semantics {
                contentDescription = "This is the main layout containing the buttons"
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ResetCoachScreenButton(
            onResetCoachScreenClicked = onResetCoachScreenClicked
        )
        CoachGenerateResponseButton(
            coachState = coachState,
            onGenerateResponseClicked = onGenerateResponseClicked,
            modifier = Modifier
                .weight(1f)
                .height(Dimension.d48)
        )
    }
}
