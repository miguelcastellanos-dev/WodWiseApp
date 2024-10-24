package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun AddWeightInHistoryFAB(
    modifier: Modifier = Modifier,
    weightHistoryFABClicked: () -> Unit,
) {
    FloatingActionButton(
        onClick = weightHistoryFABClicked,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primary,
        contentColor = MaterialTheme.colorScheme.onPrimary
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "Add weight in history button"
        )
    }
}
