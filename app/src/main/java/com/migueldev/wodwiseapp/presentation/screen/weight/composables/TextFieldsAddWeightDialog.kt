package com.migueldev.wodwiseapp.presentation.screen.weight.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState

@Composable
fun TextFieldsAddWeightDialog(
    weightsState: WeightsState,
    weightName: String,
    setWeightName: (String) -> Unit,
    weightRm: String,
    setWeightRm: (String) -> Unit,
) {
    Column {
        TextField(
            value = weightName,
            onValueChange = { newValue -> setWeightName(newValue) },
            label = {
                Text(
                    text = weightsState.textFieldAddExerciseNameDialogText,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 0.5f
                ),
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 0.3f
                )
            )
        )
        Spacer(modifier = Modifier.padding(Dimension.d8))
        TextField(
            value = weightRm,
            onValueChange = { newValue -> setWeightRm(newValue) },
            label = {
                Text(
                    text = weightsState.textFieldAddRmWeightDialogText,
                    color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.3f)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 0.5f
                ),
                unfocusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                    alpha = 0.3f
                )
            )
        )
    }
}
