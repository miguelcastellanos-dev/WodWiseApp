package com.migueldev.wodwiseapp.presentation.screen.coach.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachViewModel
import kotlinx.coroutines.launch

@Composable
fun createCoachActionState(
    coachViewModel: CoachViewModel,
    coachState: CoachState,
): CoachActionState {
    val coroutineScope = rememberCoroutineScope()
    return CoachActionState(
        onPreviousFormatClicked = { coachViewModel.onFormatNavigationClicked(false) },
        onNextFormatClicked = { coachViewModel.onFormatNavigationClicked(true) },
        onGenerateResponseClicked = {
            coachViewModel.createInfo(
                selectedTime = coachState.selectedTime,
                selectedFormat = coachState.selectedFormat,
                selectedExerciseLists = coachState.selectedExerciseLists
            )
        },
        onDismissResponseDialogClicked = {
            coachViewModel.handleDialogState(false)
        },
        onSaveResponseDialogClicked = {
            coroutineScope.launch {
                coachViewModel.saveIntelligenceWorkout(
                    coachState.answerOpenAi!!
                )
            }
        },
        onSelectedTimeChange = { coachViewModel.updateSelectedTime(it) },
        onExerciseSelected = { exercise, selected ->
            coachViewModel.onExerciseSelected(exerciseName = exercise, selected = selected)
        },
        onResetCoachScreenClicked = {
            coachViewModel.resetAllExercises()
            coachViewModel.foldAllExerciseTabs()
        }
    )
}
