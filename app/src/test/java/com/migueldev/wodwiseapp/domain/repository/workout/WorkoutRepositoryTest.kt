package com.migueldev.wodwiseapp.domain.repository.workout

import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.core.coVerifyOnce
import com.migueldev.wodwiseapp.core.relaxedMockk
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.data.remote.datasource.workout.WorkoutDatasource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class WorkoutRepositoryTest {

    private val remoteDatasource = relaxedMockk<WorkoutDatasource>()

    private lateinit var workoutRepository: WorkoutRepository

    @BeforeEach
    fun setUp() {
        workoutRepository = WorkoutRepository(remoteDatasource)
    }

    @Test
    fun `GIVEN valid WorkoutDto WHEN addWorkout is called THEN it calls remoteDatasource`(): Unit =
        runTest {
            val workoutId = "12345L"
            val workoutDto = WorkoutDto(
                workoutId = workoutId,
                date = Timestamp(seconds = 1721772240, nanoseconds = 615000000),
                session = "Session 1",
                positionSession = "A",
                exerciseType = "Run",
                instructions = "Instructions",
                checkboxState = false,
                notes = ""
            )

            workoutRepository.addWorkoutToFirestore(workoutId, workoutDto)

            coVerifyOnce { remoteDatasource.addWorkoutToFirestore(workoutId, workoutDto) }
        }

    @Test
    fun `GIVEN valid workoutId WHEN removeWorkout is called THEN it should call remoteDatasource`(): Unit =
        runTest {
            val workoutId = "workout123"

            workoutRepository.removeWorkout(workoutId)

            coVerifyOnce { remoteDatasource.removeWorkout(workoutId) }
        }

    @Test
    fun `GIVEN workoutId and state WHEN updateCheckboxState is called THEN call remoteDatasource`(): Unit =
        runTest {
            val workoutId = "workout123"
            val newState = true

            workoutRepository.updateCheckboxState(workoutId, newState)

            coVerifyOnce { remoteDatasource.updateCheckboxState(workoutId, newState) }
        }
}
