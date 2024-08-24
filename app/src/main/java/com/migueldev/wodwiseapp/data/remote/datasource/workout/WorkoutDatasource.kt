package com.migueldev.wodwiseapp.data.remote.datasource.workout

import com.migueldev.wodwiseapp.data.dto.WorkoutDto

interface WorkoutDatasource {

    suspend fun addWorkoutToFirestore(
        dto: WorkoutDto,
    )

    suspend fun removeWorkout(id: String)

    suspend fun updateCheckboxState(workoutId: String, newState: Boolean)
}
