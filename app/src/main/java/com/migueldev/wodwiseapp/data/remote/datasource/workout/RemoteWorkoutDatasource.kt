package com.migueldev.wodwiseapp.data.remote.datasource.workout

import com.google.firebase.firestore.FirebaseFirestore
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.data.mapper.WORKOUT_CHECKBOX_STATE_FIELD
import com.migueldev.wodwiseapp.data.mapper.toMap
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.domain.usecase.GenerateWorkoutIdUseCase
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.tasks.await

class RemoteWorkoutDatasource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val userPreferences: UserPreferences,
    private val generateWorkoutIdUseCase: GenerateWorkoutIdUseCase,
) : WorkoutDatasource {

    private suspend fun getUserCollection(): String {
        val email = userPreferences.getEmail().first()
        return email + WORKOUTS_COLLECTION_SUFFIX
    }

    override suspend fun addWorkoutToFirestore(dto: WorkoutDto) {
        val id = generateWorkoutIdUseCase()
        val model = dto.copy(workoutId = id).toMap()
        val userCollection = getUserCollection()
        firebaseFirestore
            .collection(userCollection)
            .document(id)
            .set(model)
            .await()
    }

    override suspend fun updateCheckboxState(workoutId: String, newState: Boolean) {
        val model = mapOf(WORKOUT_CHECKBOX_STATE_FIELD to newState)
        val userCollection = getUserCollection()
        firebaseFirestore.collection(userCollection).document(workoutId).update(model).await()
    }

    override suspend fun removeWorkout(id: String) {
        firebaseFirestore.collection(
            userPreferences.getEmail().first() + WORKOUTS_COLLECTION_SUFFIX
        ).document(id).delete()
    }
}

private const val WORKOUTS_COLLECTION_SUFFIX = "_workouts"
