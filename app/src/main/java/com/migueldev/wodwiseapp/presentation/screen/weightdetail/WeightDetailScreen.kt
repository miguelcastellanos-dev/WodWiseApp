package com.migueldev.wodwiseapp.presentation.screen.weightdetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.theme.RotatingImageLoading
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.AddWeightHistoryDialog
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.WeightDetailScreenContent

@Composable
fun WeightDetailScreen(
    appState: AppState,
    weightId: String,
    appActionState: AppActionState,
    addWeightHistoryClicked: (String, Double, Int, String) -> Unit,
) {
    val exerciseWeight = appState.weightState.exercisesWeightList.find { it.weightId == weightId }

    if (appState.weightState.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            RotatingImageLoading(
                imageResource = R.drawable.ic_launcher_round,
                modifier = Modifier.align(Alignment.Center),
                size = Dimension.d128
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            if (exerciseWeight != null) {
                WeightDetailScreenContent(
                    appState = appState,
                    exerciseWeight = exerciseWeight,
                    appActionState = appActionState,
                    modifier = Modifier.align(Alignment.BottomEnd)
                )
            } else {
                Text(text = appState.weightDetailState.emptyWeightText)
            }
        }

        if (exerciseWeight != null && appState.weightDetailState.showAddWeightDetailDialog.value) {
            AddWeightHistoryDialog(
                appState = appState,
                onDismiss = {
                    appState.weightDetailState.showAddWeightDetailDialog.value = false
                    appState.weightDetailState.weightHistory.value = ""
                    appState.weightDetailState.repetitionsHistory.value = ""
                },
                weightId = exerciseWeight.weightId,
                addWeightHistoryClicked = addWeightHistoryClicked
            )
        }
    }
}
