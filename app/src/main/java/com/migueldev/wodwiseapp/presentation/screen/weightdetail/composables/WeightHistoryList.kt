package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.WeightHistoryCard
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import me.saket.swipe.SwipeAction
import me.saket.swipe.SwipeableActionsBox

@Composable
fun WeightHistoryList(
    weightDetailState: WeightDetailState,
    exerciseWeight: WeightData,
    removeWeightHistoryClicked: (String, String) -> Unit,
) {
    val weightHistoryList = exerciseWeight.weightHistoryList

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.d8)
    ) {
        Text(
            text = weightDetailState.historyListTitle,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.padding(Dimension.d8))

        if (weightHistoryList.isEmpty()) {
            Text(
                text = weightDetailState.emptyHistoryList,
                color = Color.Gray
            )
        } else {
            weightHistoryList.forEach { weightHistoryData ->
                val swipeLeft = SwipeAction(
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "delete item",
                            modifier = Modifier.size(Dimension.d32)
                        )
                    },
                    background = Color.Red,
                    isUndo = true,
                    onSwipe = {
                        removeWeightHistoryClicked(
                            exerciseWeight.weightId,
                            weightHistoryData.idHistory
                        )
                    }
                )
                SwipeableActionsBox(endActions = listOf(swipeLeft)) {
                    WeightHistoryCard(
                        weightHistoryData = weightHistoryData,
                        weightDetailState = weightDetailState
                    )
                }
            }
        }

        Spacer(modifier = Modifier.padding(Dimension.d64))
    }
}
