package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.UpdateRmDialog
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun ScreenTitleCard(
    weightDetailState: WeightDetailState,
    weightId: String,
    exerciseName: String,
    exerciseRm: Double,
    onRmChange: (String, Double) -> Unit,
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        UpdateRmDialog(
            weightDetailState = weightDetailState,
            initialValue = exerciseRm.toString(),
            onDismiss = { showDialog = false },
            onConfirm = { newRm ->
                val newRmValue = newRm.toDoubleOrNull()
                if (newRmValue != null) {
                    onRmChange(weightId, newRmValue)
                }
            }
        )
    }
    Card(
        modifier = Modifier
            .padding(Dimension.d8),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.d8),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.d8),
                text = exerciseName,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 36.sp
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            UpdateWeightButton(
                onButtonClick = { showDialog = true },
                weightDetailState = weightDetailState,
                exerciseRm = exerciseRm
            )
        }
    }
}
