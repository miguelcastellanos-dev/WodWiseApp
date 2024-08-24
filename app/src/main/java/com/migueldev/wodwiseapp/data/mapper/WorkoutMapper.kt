package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.dto.WorkoutDto

fun WorkoutDto.toMap(): Map<String, Any> {
    return mapOf(
        WORKOUT_ID_FIELD to workoutId,
        WORKOUT_DATE_FIELD to date,
        WORKOUT_SESSION_FIELD to session,
        WORKOUT_POSITION_SESSION_FIELD to positionSession,
        WORKOUT_EXERCISE_TYPE_FIELD to exerciseType,
        WORKOUT_INSTRUCTIONS_FIELD to instructions,
        WORKOUT_CHECKBOX_STATE_FIELD to checkboxState,
        WORKOUT_NOTES_STATE_FIELD to notes
    )
}

const val WORKOUT_ID_FIELD = "workoutId"
const val WORKOUT_DATE_FIELD = "date"
const val WORKOUT_SESSION_FIELD = "session"
const val WORKOUT_POSITION_SESSION_FIELD = "positionSession"
const val WORKOUT_EXERCISE_TYPE_FIELD = "exerciseType"
const val WORKOUT_INSTRUCTIONS_FIELD = "instructions"
const val WORKOUT_CHECKBOX_STATE_FIELD = "checkboxState"
const val WORKOUT_NOTES_STATE_FIELD = "notes"
