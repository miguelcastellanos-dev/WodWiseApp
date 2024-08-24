package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarDeleteIconButton(
    modifier: Modifier,
    workoutId: String,
    onDeleteIconClicked: (String) -> Unit,
) {
    Box(
        modifier = modifier
            .size(Dimension.d48)
            .padding(Dimension.d8)
            .pointerInput(Unit) {
                detectTapGestures(
                    onDoubleTap = {
                        onDeleteIconClicked(workoutId)
                    }
                )
            }
    ) {
        Icon(
            imageVector = Icons.Filled.Delete,
            contentDescription = "deleteIcon",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}
