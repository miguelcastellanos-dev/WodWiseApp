package com.migueldev.wodwiseapp.data.mapper

import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.data.remote.response.WorkoutResponse
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.Test

class WorkoutResponseToDomainTest {

    @Test
    fun `GIVEN valid WorkoutResponse WHEN toDomain is called THEN return WorkoutCardData`() {
        val workoutId = "123"
        val date = Timestamp(seconds = 1721772240, nanoseconds = 615000000)
        val session = "Session 1"
        val positionSession = "A"
        val exerciseType = "Strength"
        val instructions = "Do 10 pushups"
        val checkboxState = true
        val notes = "notes"
        val workoutResponse = WorkoutResponse(
            workoutId = workoutId,
            date = date,
            session = session,
            positionSession = positionSession,
            exerciseType = exerciseType,
            instructions = instructions,
            checkboxState = checkboxState,
            notes = notes
        )

        val result = workoutResponse.toDomain()

        workoutId shouldBeEqualTo result.workoutId
        session shouldBeEqualTo result.session
        positionSession shouldBeEqualTo result.positionSession
        exerciseType shouldBeEqualTo result.exerciseType
        instructions shouldBeEqualTo result.instructions
        checkboxState shouldBeEqualTo result.checkboxState
        notes shouldBeEqualTo result.notes
    }
}
