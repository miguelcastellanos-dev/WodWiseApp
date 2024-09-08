package com.migueldev.wodwiseapp.presentation.screen.weight

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.theme.RotatingImageLoading
import com.migueldev.wodwiseapp.presentation.screen.weight.composables.AddWeightDialog
import com.migueldev.wodwiseapp.presentation.screen.weight.composables.AddWeightFAB
import com.migueldev.wodwiseapp.presentation.screen.weight.composables.WeightList
import kotlinx.coroutines.launch

@Composable
fun WeightScreenContent(
    weightViewModel: WeightViewModel,
    appActionState: AppActionState,
) {
    val weightsState by weightViewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        weightViewModel.getWeightsFromDatabase()
    }

    if (weightsState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            RotatingImageLoading(
                imageResource = R.drawable.app_icon,
                modifier = Modifier.align(Alignment.Center),
                size = Dimension.d128
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            WeightList(
                weights = weightsState.exercisesWeightList,
                onItemRemove = {
                    coroutineScope.launch {
                        weightViewModel.removeWeight(it)
                    }
                },
                onWeightClicked = appActionState.onWeightClicked
            )
            AddWeightFAB(
                weightsState = weightsState,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(Dimension.d16),
                onClick = { weightsState.showAddWeightDialog.value = true }
            )
        }
        if (weightsState.showAddWeightDialog.value) {
            AddWeightDialog(
                weightsState = weightsState,
                onDismiss = { weightsState.showAddWeightDialog.value = false },
                onConfirm = { exerciseName, exerciseRm ->
                    coroutineScope.launch {
                        weightViewModel.saveWeight(exerciseName, exerciseRm)
                        weightsState.showAddWeightDialog.value = false
                    }
                }
            )
        }
    }
}
