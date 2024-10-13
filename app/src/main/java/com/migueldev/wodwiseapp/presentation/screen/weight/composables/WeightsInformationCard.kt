package com.migueldev.wodwiseapp.presentation.screen.weight.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState

@Composable
fun WeightsInformationCard(
    weightsState: WeightsState,
) {
    Card(
        modifier = Modifier.padding(
            start = Dimension.d8,
            bottom = Dimension.d8
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        ),
        border = BorderStroke(
            Dimension.d1,
            MaterialTheme.colorScheme.primary
        )
    ) {
        LazyColumn(modifier = Modifier.padding(Dimension.d8)) {
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Dimension.d8),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = weightsState.weightInformationTitleText,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = "Information icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
            item {
                Text(
                    modifier = Modifier.padding(Dimension.d8),
                    text = weightsState.weightInformationText,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Start
                )
            }
        }
    }
}
