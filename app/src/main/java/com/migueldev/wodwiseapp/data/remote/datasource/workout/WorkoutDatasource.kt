package com.migueldev.wodwiseapp.data.remote.datasource.workout

import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import kotlinx.coroutines.flow.Flow

interface WorkoutDatasource {
    fun getWorkouts(): Flow<List<WorkoutCardData>>

    suspend fun addWorkoutToFirestore(
        documentId: String,
        dto: WorkoutDto,
    )

    suspend fun removeWorkout(id: String)

    suspend fun updateCheckboxState(workoutId: String, newState: Boolean)

    suspend fun updateNotesState(workoutId: String, newState: String)

    suspend fun updateInstructionsState(workoutId: String, newState: String)

    suspend fun deleteWorkoutCollection()
}
