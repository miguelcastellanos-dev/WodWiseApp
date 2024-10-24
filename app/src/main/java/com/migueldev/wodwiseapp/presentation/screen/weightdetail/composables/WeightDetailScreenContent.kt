package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.PercentagesCard
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.RepetitionCard
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.detailScreenCards.ScreenTitleCard

@Composable
fun WeightDetailScreenContent(
    appState: AppState,
    exerciseWeight: WeightData,
    appActionState: AppActionState,
    modifier: Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(MaterialTheme.colorScheme.background)
    ) {
        ScreenTitleCard(
            weightDetailState = appState.weightDetailState,
            weightId = exerciseWeight.weightId,
            exerciseName = exerciseWeight.weightName,
            exerciseRm = exerciseWeight.weightRepetitionMaximum,
            onRmChange = appActionState.onRmChangeClicked
        )
        PercentagesCard(
            weightDetailState = appState.weightDetailState,
            exerciseRm = exerciseWeight.weightRepetitionMaximum
        )
        RepetitionCard(
            weightDetailState = appState.weightDetailState,
            exerciseRm = exerciseWeight.weightRepetitionMaximum
        )
        WeightHistoryList(
            weightDetailState = appState.weightDetailState,
            exerciseWeight = exerciseWeight,
            removeWeightHistoryClicked = appActionState.removeWeightHistoryClicked
        )
    }
    AddWeightInHistoryFAB(
        modifier = modifier
            .padding(Dimension.d16),
        weightHistoryFABClicked = {
            appState.weightDetailState.showAddWeightDetailDialog.value = true
        }
    )
}
