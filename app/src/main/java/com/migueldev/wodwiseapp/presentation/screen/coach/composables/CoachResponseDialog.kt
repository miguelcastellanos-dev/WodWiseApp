package com.migueldev.wodwiseapp.presentation.screen.coach.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachResponseDialog(
    coachState: CoachState,
    onDismissResponseDialog: () -> Unit,
    onSaveResponseDialog: () -> Unit,
) {
    Dialog(onDismissRequest = onDismissResponseDialog) {
        Surface(
            shape = MaterialTheme.shapes.medium
        ) {
            Column(
                modifier = Modifier.padding(Dimension.d16),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = coachState.responseDialogTitleText,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(Dimension.d8))
                Text(text = coachState.answerOpenAi!!)
                Spacer(modifier = Modifier.height(Dimension.d16))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(onClick = onDismissResponseDialog) {
                        Text(coachState.responseDialogExitButtonText)
                    }
                    Spacer(modifier = Modifier.width(Dimension.d8))
                    Button(onClick = {
                        onSaveResponseDialog()
                        onDismissResponseDialog()
                    }) {
                        Text(coachState.responseDialogSaveButtonText)
                    }
                }
            }
        }
    }
}
