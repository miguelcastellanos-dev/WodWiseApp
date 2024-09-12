package com.migueldev.wodwiseapp.presentation.screen.profile.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun DeleteUserClickableText(
    profileState: ProfileState,
    onDeleteUserClicked: () -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        WarningAlertDialog(
            profileState = profileState,
            onClick = onDeleteUserClicked,
            onDismiss = { showDialog = false },
            warningText = profileState.deleteUserDialogWarningText
        )
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.d16)
    ) {
        Column {
            Text(
                text = profileState.deleteUserText,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Red,
                modifier = Modifier.clickable {
                    showDialog = true
                }
            )
            Text(
                text = profileState.deleteUserWarningText,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}