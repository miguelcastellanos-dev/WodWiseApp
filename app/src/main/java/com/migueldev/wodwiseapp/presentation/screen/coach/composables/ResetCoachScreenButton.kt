package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun ResetCoachScreenButton(
    onResetCoachScreenClicked: () -> Unit,
) {
    IconButton(
        onClick = {
            onResetCoachScreenClicked()
        },
        modifier = Modifier
            .size(Dimension.d48)
            .padding(
                top = Dimension.d8,
                end = Dimension.d8
            ),
        colors = IconButtonDefaults.iconButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContentColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = "Reset screen button"
        )
    }
}
