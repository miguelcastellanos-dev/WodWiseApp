package com.migueldev.wodwiseapp.presentation.screen.coach

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.coach.composables.CoachFormatAndTimeCard
import com.migueldev.wodwiseapp.presentation.screen.coach.composables.CoachGenerateResponseButton
import com.migueldev.wodwiseapp.presentation.screen.coach.composables.CoachResponseDialog
import com.migueldev.wodwiseapp.presentation.screen.coach.composables.exercises.CoachExerciseCard
import com.migueldev.wodwiseapp.presentation.screen.coach.composables.exercises.SelectedExercisesCards
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.getExerciseSections
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CoachScreenContent(
    coachState: CoachState,
    coachViewModel: CoachViewModel,
    coachActionState: CoachActionState,
) {
    LaunchedEffect(coachState.answerOpenAi) {
        coachViewModel.handleDialogState(true)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimension.d8)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            contentPadding = PaddingValues(bottom = Dimension.d48)
        ) {
            item {
                CoachFormatAndTimeCard(
                    coachState = coachState,
                    coachActionState = coachActionState
                )
            }
            item {
                Spacer(modifier = Modifier.height(Dimension.d16))
            }
            items(getExerciseSections(coachState)) { section ->
                CoachExerciseCard(
                    coachState = coachState,
                    coachViewModel = coachViewModel,
                    coachExerciseData = section.data,
                    onExerciseSelected = coachActionState.onExerciseSelected,
                    exerciseHeaderText = section.headerText
                )
                Spacer(modifier = Modifier.height(Dimension.d16))
            }
        }
        if (coachViewModel.showDialog()) {
            CoachResponseDialog(
                coachState = coachState,
                onDismissResponseDialog = coachActionState.onDismissResponseDialogClicked,
                onSaveResponseDialog = coachActionState.onSaveResponseDialogClicked
            )
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CoachGenerateResponseButton(
                coachState = coachState,
                onGenerateResponseClicked = coachActionState.onGenerateResponseClicked,
                modifier = Modifier
            )
            SelectedExercisesCards(
                coachState = coachState
            )
        }
    }
}
