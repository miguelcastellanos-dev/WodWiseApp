package com.migueldev.wodwiseapp.presentation.screen.weight.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun WeightList(
    weightsState: WeightsState,
    weights: List<WeightData>,
    onItemRemove: (String) -> Unit,
    onWeightClicked: (String) -> Unit,
) {
    if (weights.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.d16),
            contentAlignment = Alignment.Center
        ) {
            WeightsInformationCard(
                weightsState = weightsState
            )
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Dimension.d8)
        ) {
            items(weights) { weight ->
                val swipeLeft = SwipeAction(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete item",
                            modifier = Modifier
                                .size(Dimension.d32)
                        )
                    },
                    background = Color.Red,
                    isUndo = true,
                    onSwipe = { onItemRemove(weight.weightId) }
                )
                SwipeableActionsBox(endActions = listOf(swipeLeft)) {
                    WeightListCard(
                        weight = weight,
                        onWeightClicked = onWeightClicked
                    )
                }
            }
        }
    }
}
