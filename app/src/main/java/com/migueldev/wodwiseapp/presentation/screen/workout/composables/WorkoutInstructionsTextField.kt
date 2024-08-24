package com.migueldev.wodwiseapp.presentation.screen.workout.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

@Composable
fun WorkoutInstructionsTextField(
    workoutState: WorkoutState,
    value: String,
    onTextChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = value,
            onValueChange = {
                onTextChanged(it)
            },
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = Dimension.d128),
            placeholder = {
                Text(workoutState.placeholderInstructionsText)
            },
            trailingIcon = {
                if (value.isNotEmpty()) {
                    IconButton(onClick = { onTextChanged("") }) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = "Clear text"
                        )
                    }
                }
            },
            maxLines = Int.MAX_VALUE,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                focusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedIndicatorColor = MaterialTheme.colorScheme.primary,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.onPrimaryContainer,
                unfocusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer,
                focusedPlaceholderColor = MaterialTheme.colorScheme.onPrimaryContainer
            )
        )
    }
}
