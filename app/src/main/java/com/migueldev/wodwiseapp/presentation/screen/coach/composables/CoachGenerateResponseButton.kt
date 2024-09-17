package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.theme.RotatingImageLoading

@Composable
fun CoachGenerateResponseButton(
    coachState: CoachState,
    onGenerateResponseClicked: () -> Unit,
    modifier: Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Button(
            onClick = { onGenerateResponseClicked() },
            modifier = Modifier
                .fillMaxWidth(),
            enabled = !coachState.isLoading,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
                contentColor = MaterialTheme.colorScheme.onPrimary,
                disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        ) {
            if (coachState.isLoading) {
                RotatingImageLoading(
                    imageResource = R.drawable.ic_launcher_round,
                    modifier = Modifier,
                    size = Dimension.d32
                )
            } else {
                Text(text = coachState.generateResponseButtonText)
            }
        }
    }
}
