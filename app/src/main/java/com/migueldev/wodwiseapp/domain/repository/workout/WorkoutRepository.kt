package com.migueldev.wodwiseapp.domain.repository.workout

import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.data.remote.datasource.workout.WorkoutDatasource
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class WorkoutRepository @Inject constructor(
    private val remoteDatasource: WorkoutDatasource,
) {
    fun getWorkouts(): Flow<List<WorkoutCardData>> {
        return remoteDatasource.getWorkouts()
    }

    suspend fun addWorkoutToFirestore(dto: WorkoutDto) {
        return remoteDatasource.addWorkoutToFirestore(dto)
    }

    suspend fun removeWorkout(id: String) {
        return remoteDatasource.removeWorkout(id)
    }

    suspend fun updateCheckboxState(workoutId: String, newState: Boolean) {
        return remoteDatasource.updateCheckboxState(workoutId, newState)
    }
}
