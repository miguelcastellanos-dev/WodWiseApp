package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightHistoryData

@Composable
fun WeightHistoryCard(
    weightHistoryData: WeightHistoryData,
    weightDetailState: WeightDetailState,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.d4),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RectangleShape,
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.d16),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text =
                "${weightHistoryData.repetitionsHistory} " +
                    "${weightDetailState.repsText} " +
                    weightDetailState.openingParenthesis +
                    "${weightHistoryData.weightHistory} " +
                    weightDetailState.abbreviationsForUnitsOfWeight +
                    weightDetailState.closingParenthesis,
                modifier = Modifier.weight(1f),
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )

            Text(
                text = weightHistoryData.dateHistory,
                modifier = Modifier.padding(end = Dimension.d16),
                style = TextStyle(fontWeight = FontWeight.Normal),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
    }
}
