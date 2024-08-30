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
fun CoachTimeSelector(
    coachState: CoachState,
    modifier: Modifier = Modifier,
    onSelectedTimeChange: (Int) -> Unit,
) {
    val selectedTime = coachState.selectedTime

    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                if (selectedTime > RANGE_MIN) {
                    onSelectedTimeChange(selectedTime - TIME_DECREMENT_IN_MINUTES)
                }
            }
        ) {
            Text(text = coachState.subtractMinutes)
        }
        Spacer(modifier = Modifier.width(Dimension.d16))
        Text(
            text = "$selectedTime ${coachState.suffixMinutes}",
            fontSize = 16.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.width(Dimension.d16))
        Button(
            onClick = {
                if (selectedTime < RANGE_MAX) {
                    onSelectedTimeChange(selectedTime + TIME_INCREMENT_IN_MINUTES)
                }
            }
        ) {
            Text(text = coachState.addMinutes)
        }
    }
}

private const val RANGE_MAX = 90
private const val RANGE_MIN = 0
private const val TIME_INCREMENT_IN_MINUTES = 1
private const val TIME_DECREMENT_IN_MINUTES = 1
