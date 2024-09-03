package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.percentageAndRepetitionTable

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.ExerciseConstants.LowerReps
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.getBackgroundColor
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper.getTextColor

@Composable
fun LeftColumnOfRepetitions(
    weightDetailState: WeightDetailState,
    exerciseRm: Double,
) {
    val repsList = LowerReps
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
