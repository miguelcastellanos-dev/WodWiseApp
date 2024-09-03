package com.migueldev.wodwiseapp.presentation.screen.weightdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.screen.weight.WeightViewModel
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.PercentagesCard
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.RepetitionCard
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.ScreenTitleCard
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun WeightDetailScreen(
    weightViewModel: WeightViewModel,
    weightDetailState: WeightDetailState,
    weightId: String,
    appActionState: AppActionState,
) {
    val weightsState by weightViewModel.state.collectAsState()
    val exerciseWeight = weightsState.exercisesWeightList.find { it.weightId == weightId }
    if (exerciseWeight != null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(MaterialTheme.colorScheme.background)
        ) {
            ScreenTitleCard(
                weightDetailState = weightDetailState,
                weightId = exerciseWeight.weightId,
                exerciseName = exerciseWeight.weightName,
                exerciseRm = exerciseWeight.weightRepetitionMaximum,
                onRmChange = appActionState.onRmChangeClicked
            )
            PercentagesCard(weightDetailState, exerciseWeight.weightRepetitionMaximum)
            RepetitionCard(weightDetailState, exerciseWeight.weightRepetitionMaximum)
        }
    } else {
        Text(text = weightDetailState.emptyWeightText)
    }
}
