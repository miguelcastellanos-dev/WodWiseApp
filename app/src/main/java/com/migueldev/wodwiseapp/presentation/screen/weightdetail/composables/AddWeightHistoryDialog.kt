package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.validateDateFormat

@Composable
fun AddWeightHistoryDialog(
    appState: AppState,
    onDismiss: () -> Unit,
    weightId: String,
    addWeightHistoryClicked: (String, Double, Int, String) -> Unit,
) {
    var enteredDate by remember { mutableStateOf("") }
    var isDateValid by remember { mutableStateOf(false) }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        onDismissRequest = { onDismiss() },
        confirmButton = {
            AddWeightHistoryConfirmButton(
                appState = appState,
                isDateValid = isDateValid,
                addWeightHistoryClicked = addWeightHistoryClicked,
                weightId = weightId,
                enteredDate = enteredDate,
                onDismiss = onDismiss
            )
        },
        dismissButton = {
            Button(
                onClick = { onDismiss() },
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                )
            ) {
                Text(appState.weightState.dismissButtonDialogText)
            }
        },
        title = {
            Text(
                text = appState.weightDetailState.weightHistoryDialogTitle,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextFieldsAddWeightHistoryDialog(
                    weightDetailState = appState.weightDetailState,
                    weightHistory = appState.weightDetailState.weightHistory.value,
                    setWeightHistory = { appState.weightDetailState.weightHistory.value = it },
                    repetitionsHistory = appState.weightDetailState.repetitionsHistory.value,
                    setRepetitionsHistory = {
                        appState.weightDetailState.repetitionsHistory.value = it
                    }
                )

                DateInputTextField(
                    weightDetailState = appState.weightDetailState,
                    onDateChange = { date ->
                        enteredDate = date
                        isDateValid = validateDateFormat(date)
                    }
                )
            }
        }
    )
}
