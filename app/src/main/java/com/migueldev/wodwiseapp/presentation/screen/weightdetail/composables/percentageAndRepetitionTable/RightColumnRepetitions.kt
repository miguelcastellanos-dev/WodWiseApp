package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.percentageAndRepetitionTable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.ExerciseConstants.HigherReps
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.getBackgroundColor
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.getTextColor

@Composable
fun RightColumnRepetitions(
    weightDetailState: WeightDetailState,
    exerciseRm: Double,
) {
    val repsList = HigherReps
    Column {
        repsList.forEachIndexed { index, reps ->
            RepetitionText(
                weightDetailState = weightDetailState,
                reps = reps,
                exerciseRm = exerciseRm,
                backgroundColor = getBackgroundColor(index),
                textColor = getTextColor(index)
            )
        }
    }
}
