package com.migueldev.wodwiseapp.presentation.screen.workout.data

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class TextResourceProvider @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun initializeTextResources(): WorkoutState {
        return WorkoutState(
            sessionItems = listOf(
                SessionItem(resourceProvider.getString(R.string.session_1)),
                SessionItem(resourceProvider.getString(R.string.session_2)),
                SessionItem(resourceProvider.getString(R.string.session_3))
            ),
            positionSessionItems = listOf(
                PositionSessionItem(resourceProvider.getString(R.string.position_a)),
                PositionSessionItem(resourceProvider.getString(R.string.position_b)),
                PositionSessionItem(resourceProvider.getString(R.string.position_c)),
                PositionSessionItem(resourceProvider.getString(R.string.position_d)),
                PositionSessionItem(resourceProvider.getString(R.string.position_e)),
                PositionSessionItem(resourceProvider.getString(R.string.position_f))
            ),
            exerciseTypeItems = listOf(
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_wod)),
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_practice)),
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_conditioning)),
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_accessory_work)),
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_strength)),
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_weightlifting)),
                ExerciseTypeItem(resourceProvider.getString(R.string.exercise_gymnastics))
            ),
            stopRecordingText = resourceProvider.getString(
                R.string.stop_recording
            ),
            startRecordingText = resourceProvider.getString(
                R.string.start_recording
            ),
            placeholderInstructionsText = resourceProvider.getString(
                R.string.placeholder_instructions
            ),
            selectDateTitleText = resourceProvider.getString(
                R.string.select_date_title
            ),
            saveWorkoutButtonText = resourceProvider.getString(
                R.string.save_workout_button
            ),
            trainingInfoSystemInstructionsText = resourceProvider.getString(
                R.string.training_info_system_instructions
            ),
            savedWorkoutToastText = resourceProvider.getString(
                R.string.saved_workout_toast_text
            )
        )
    }
}
