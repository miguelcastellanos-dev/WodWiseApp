package com.migueldev.wodwiseapp.presentation.screen.weight.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState

@Composable
fun AddWeightFAB(
    weightsState: WeightsState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = weightsState.contentDescriptionFab
        )
    }
}
