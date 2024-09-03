package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun getBackgroundColor(index: Int): Color {
    return if (index % 2 == 0) {
        MaterialTheme.colorScheme.background
    } else {
        MaterialTheme.colorScheme.primaryContainer
    }
}

@Composable
fun getTextColor(index: Int): Color {
    return if (index % 2 == 0) {
        MaterialTheme.colorScheme.onBackground
    } else {
        MaterialTheme.colorScheme.onPrimaryContainer
    }
}
