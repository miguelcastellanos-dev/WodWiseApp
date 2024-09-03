package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.percentageAndRepetitionTable.RepetitionTextRow
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun RepetitionCard(
    weightDetailState: WeightDetailState,
    exerciseRm: Double,
) {
    var isRepsExpanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .padding(Dimension.d8),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.onPrimaryContainer)
    ) {
        Column {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(Dimension.d16)
                    .clickable { isRepsExpanded = !isRepsExpanded },
                text = weightDetailState.titleRepsDetailViewText,
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                ),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
            if (isRepsExpanded) {
                RepetitionTextRow(exerciseRm, weightDetailState)
            }
        }
    }
}
