package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachFormatSelector(
    modifier: Modifier = Modifier,
    coachState: CoachState,
    onPreviousFormatClicked: () -> Unit,
    onNextFormatClicked: () -> Unit,
) {
    val formatListTexts = coachState.formatListTexts
    val selectedFormat = coachState.selectedFormat
    val selectedIndex = formatListTexts.indexOf(selectedFormat)

    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onPreviousFormatClicked
        ) {
            Text(text = coachState.previousFormatArrow)
        }
        Spacer(modifier = Modifier.width(Dimension.d16))
        Text(
            text = formatListTexts[selectedIndex],
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(Dimension.d16))
        Button(
            onClick = onNextFormatClicked
        ) {
            Text(text = coachState.nextFormatArrow)
        }
    }
}
