package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.percentageAndRepetitionTable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.calculateWeightForReps

@Composable
fun RepetitionText(
    weightDetailState: WeightDetailState,
    reps: Int,
    exerciseRm: Double,
    backgroundColor: Color,
    textColor: Color,
) {
    val repsResult = calculateWeightForReps(reps, exerciseRm)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = backgroundColor)
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(Dimension.d4),
            text = "$reps ${weightDetailState.exerciseRmTitleText} ",
            fontSize = (12.sp),
            color = textColor,
            textAlign = TextAlign.Right
        )
        Text(
            modifier = Modifier
                .padding(Dimension.d4),
            text = repsResult,
            fontSize = (21.sp),
            color = textColor
        )
    }
}