package com.migueldev.wodwiseapp.data.mapper

import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class WorkoutDtoToMapTest {

    @Test
    fun `GIVEN workoutDto WHEN toMap called THEN return correct Map`() {
        val workoutDto = WorkoutDto(
            workoutId = "1L",
            date = Timestamp.now(),
            session = "Sesion 1",
            positionSession = "A",
            exerciseType = "WOD",
            instructions = "Run 5km",
            checkboxState = false,
            notes = "notes"
        )

        val expectedMap = mapOf(
            WORKOUT_ID_FIELD to workoutDto.workoutId,
            WORKOUT_DATE_FIELD to workoutDto.date,
            WORKOUT_SESSION_FIELD to workoutDto.session,
            WORKOUT_POSITION_SESSION_FIELD to workoutDto.positionSession,
            WORKOUT_EXERCISE_TYPE_FIELD to workoutDto.exerciseType,
            WORKOUT_INSTRUCTIONS_FIELD to workoutDto.instructions,
            WORKOUT_CHECKBOX_STATE_FIELD to workoutDto.checkboxState,
            WORKOUT_NOTES_STATE_FIELD to workoutDto.notes
        )

        val resultMap = workoutDto.toMap()

        resultMap shouldBeEqualTo expectedMap
    }
}
