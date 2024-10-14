package com.migueldev.wodwiseapp.presentation.screen.coach.composables.exercises

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachExerciseHeader(
    text: String,
    showDayCardState: Boolean,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(Dimension.d8),
            fontSize = 16.sp
        )
        Icon(
            imageVector =
            if (showDayCardState) {
                Icons.Default.ArrowDropUp
            } else {
                Icons.Default.ArrowDropDown
            },
            contentDescription = "Expand icon",
            modifier = Modifier
                .size(Dimension.d48)
                .padding(end = Dimension.d16),
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
}
