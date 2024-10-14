package com.migueldev.wodwiseapp.presentation.screen.weight.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData

@Composable
fun WeightListCard(
    weight: WeightData,
    onWeightClicked: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimension.d8)
            .clickable {
                onWeightClicked(weight.weightId)
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        shape = RectangleShape,
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Row {
            Text(
                text = weight.weightName,
                modifier = Modifier
                    .padding(Dimension.d16)
                    .weight(1F),
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Text(
                text = weight.weightRepetitionMaximum.toString(),
                modifier = Modifier
                    .padding(Dimension.d16),
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "Detail view icon",
                modifier = Modifier
                    .size(Dimension.d48)
                    .padding(end = Dimension.d16),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}
