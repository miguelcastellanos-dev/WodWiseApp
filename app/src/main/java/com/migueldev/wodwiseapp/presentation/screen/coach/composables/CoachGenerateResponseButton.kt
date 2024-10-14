package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
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
    Button(
        onClick = { onGenerateResponseClicked() },
        modifier = modifier
            .padding(
                top = Dimension.d8
            )
            .semantics { contentDescription = "Generate Response Button" },
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
