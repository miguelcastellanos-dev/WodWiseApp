package com.migueldev.wodwiseapp.domain.usecase

import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutData
import org.amshove.kluent.shouldBeEqualTo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WorkoutDtoUseCaseTest {

    private lateinit var useCase: WorkoutDtoUseCase

    @BeforeEach
    fun setUp() {
        useCase = WorkoutDtoUseCase()
    }

    @Test
    fun `GIVEN CreateWorkoutParams WHEN createWorkoutDtoUseCase is called THEN returns WorkoutDto`() {
        val notesInitialText = ""
        val workoutData = WorkoutData(
            workoutId = "12345L",
            timeStamp = Timestamp(seconds = 1721772240, nanoseconds = 615000000),
            session = "Session 1",
            position = "A",
            exerciseType = "Run",
            instructions = "Instructions"
        )
        val workoutDto = WorkoutDto(
            workoutId = "12345L",
            date = Timestamp(seconds = 1721772240, nanoseconds = 615000000),
            session = "Session 1",
            positionSession = "A",
            exerciseType = "Run",
            instructions = "Instructions",
            checkboxState = false,
            notes = ""
        )
        val result = useCase(notesInitialText, workoutData)

        result shouldBeEqualTo workoutDto
    }
}
