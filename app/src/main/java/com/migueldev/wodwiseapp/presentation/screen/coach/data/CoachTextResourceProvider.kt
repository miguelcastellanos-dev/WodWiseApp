package com.migueldev.wodwiseapp.presentation.screen.coach.data

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createBarbellMovementsExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createBodyWeightExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createDumbbellExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createFunctionalWeightedExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createGymnasticExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createKettlebellExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createMetabolicExerciseData
import com.migueldev.wodwiseapp.presentation.screen.coach.utils.createStrengthExerciseData
import javax.inject.Inject

class CoachTextResourceProvider @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun coachInitializeTextResources(): CoachState {
        return CoachState(
            formatTitleText = resourceProvider.getString(R.string.format_title),
            timeTitleText = resourceProvider.getString(R.string.time_title),
            generateResponseButtonText = resourceProvider.getString(
                R.string.generate_training_button_text
            ),
            responseDialogTitleText = resourceProvider.getString(
                R.string.response_dialog_title
            ),
            responseDialogExitButtonText = resourceProvider.getString(
                R.string.response_dialog_button_exit
            ),
            responseDialogSaveButtonText = resourceProvider.getString(
                R.string.response_dialog_button_save
            ),
            systemInfoText = resourceProvider.getString(
                R.string.training_info_system_coach
            ),
            sessionText = resourceProvider.getString(R.string.intelligence_session),
            positionText = resourceProvider.getString(R.string.position_a),
            exerciseTypeText = resourceProvider.getString(R.string.exercise_wod),
            savedWorkoutToastText = resourceProvider.getString(
                R.string.saved_workout_toast_text
            ),
            formatListTexts = listOf(
                resourceProvider.getString(R.string.format_for_time),
                resourceProvider.getString(R.string.format_emom),
                resourceProvider.getString(R.string.format_amrap)
            ),
            barbellMovementsCoachExerciseData = createBarbellMovementsExerciseData(
                resourceProvider
            ),
            barbellMovementsHeaderText = resourceProvider.getString(
                R.string.barbell_header_text
            ),
            bodyWeightCoachExerciseData = createBodyWeightExerciseData(resourceProvider),
            bodyWeightHeaderText = resourceProvider.getString(
                R.string.body_weight_header_text
            ),
            metabolicCoachExerciseData = createMetabolicExerciseData(resourceProvider),
            metabolicHeaderText = resourceProvider.getString(
                R.string.metabolic_header_text
            ),
            dumbbellCoachExerciseData = createDumbbellExerciseData(resourceProvider),
            dumbbellHeaderText = resourceProvider.getString(
                R.string.dumbbell_header_text
            ),
            functionalWeightedCoachExerciseData = createFunctionalWeightedExerciseData(
                resourceProvider
            ),
            functionalWeightedHeaderText = resourceProvider.getString(
                R.string.functional_weighted_header_text
            ),
            gymnasticCoachExerciseData = createGymnasticExerciseData(resourceProvider),
            gymnasticsHeaderText = resourceProvider.getString(R.string.gymnastics_header_text),
            kettlebellCoachExerciseData = createKettlebellExerciseData(resourceProvider),
            kettlebellHeaderText = resourceProvider.getString(R.string.kettlebell_header_text),
            strengthCoachExerciseData = createStrengthExerciseData(resourceProvider),
            strengthHeaderText = resourceProvider.getString(R.string.strength_header_text),
            suffixMinutes = resourceProvider.getString(R.string.suffix_minutes),
            addMinutes = resourceProvider.getString(R.string.add_minutes),
            subtractMinutes = resourceProvider.getString(R.string.subtract_minutes),
            nextFormatArrow = resourceProvider.getString(R.string.next_format_arrow),
            previousFormatArrow = resourceProvider.getString(R.string.previous_format_arrow),
            notesInitialText = resourceProvider.getString(
                R.string.notes_initial_text
            )
        )
    }
}
