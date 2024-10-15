package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun TextFieldsAddWeightHistoryDialog(
    weightDetailState: WeightDetailState,
    weightHistory: String,
    setWeightHistory: (String) -> Unit,
    repetitionsHistory: String,
    setRepetitionsHistory: (String) -> Unit,
) {
    Column {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = weightHistory,
            onValueChange = { newValue ->
                val processedValue = newValue.replace(",", ".")
                setWeightHistory(processedValue)
            },
            label = {
                Text(text = weightDetailState.weightInputTextFieldLabel)
            },
            placeholder = { Text(weightDetailState.weightInputTextFieldPlaceholder) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
        )
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = repetitionsHistory,
            onValueChange = { newValue ->
                val filteredValue = newValue.filter { it.isDigit() }
                setRepetitionsHistory(filteredValue)
            },
            label = {
                Text(text = weightDetailState.repetitionsInputTextFieldLabel)
            },
            placeholder = { Text(weightDetailState.repetitionsInputTextFieldPlaceholder) },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
            textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
        )
    }
}
