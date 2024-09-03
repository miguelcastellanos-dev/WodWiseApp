package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.percentageAndRepetitionTable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.ExerciseConstants.LowPercentages
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.getBackgroundColor
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.getTextColor

@Composable
fun RightColumnPercentages(
    weightDetailState: WeightDetailState,
    exerciseRm: Double,
) {
    val percentages = LowPercentages
    Column {
        percentages.forEachIndexed { index, percentage ->
            PercentageText(
                weightDetailState = weightDetailState,
                percentage = percentage,
                exerciseRm = exerciseRm,
                backgroundColor = getBackgroundColor(index),
                textColor = getTextColor(index)
            )
        }
    }
}
