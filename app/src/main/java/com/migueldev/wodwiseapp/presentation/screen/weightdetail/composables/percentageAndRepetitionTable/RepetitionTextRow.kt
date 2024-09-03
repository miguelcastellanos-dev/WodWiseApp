package com.migueldev.wodwiseapp.presentation.screen.weightdetail.composables.percentageAndRepetitionTable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState

@Composable
fun RepetitionTextRow(
    exerciseRm: Double,
    weightDetailState: WeightDetailState,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(COLUMNS_NUMBER),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = Dimension.d0, max = Dimension.d480),
        contentPadding = PaddingValues(Dimension.d8),
        verticalArrangement = Arrangement.spacedBy(Dimension.d8),
        horizontalArrangement = Arrangement.spacedBy(Dimension.d8)
    ) {
        item {
            SelectionContainer {
                LeftColumnOfRepetitions(
                    weightDetailState = weightDetailState,
                    exerciseRm = exerciseRm
                )
            }
        }
        item {
            SelectionContainer {
                RightColumnRepetitions(
                    weightDetailState = weightDetailState,
                    exerciseRm = exerciseRm
                )
            }
        }
    }
}

private const val COLUMNS_NUMBER = 2
