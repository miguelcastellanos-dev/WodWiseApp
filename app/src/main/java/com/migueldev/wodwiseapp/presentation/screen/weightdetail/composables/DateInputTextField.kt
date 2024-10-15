package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.DateVisualTransformation
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.handleDateInputChange

@Composable
fun DateInputTextField(
    weightDetailState: WeightDetailState,
    onDateChange: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    val visualTransformation = DateVisualTransformation()

    OutlinedTextField(
        value = text,
        onValueChange = { input ->
            text = handleDateInputChange(input, onDateChange)
        },
        modifier = Modifier.fillMaxWidth(),
        label = { Text(weightDetailState.dateInputTextFieldLabel) },
        placeholder = { Text(weightDetailState.dateInputTextFieldPlaceholder) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        visualTransformation = visualTransformation,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
    )
}
