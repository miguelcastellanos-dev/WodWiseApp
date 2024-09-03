package com.migueldev.wodwiseapp.data.remote.datasource.workout

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.snapshots
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.data.mapper.WORKOUT_CHECKBOX_STATE_FIELD
import com.migueldev.wodwiseapp.data.mapper.WORKOUT_DATE_FIELD
import com.migueldev.wodwiseapp.data.mapper.toDomain
import com.migueldev.wodwiseapp.data.mapper.toMap
import com.migueldev.wodwiseapp.data.remote.response.WorkoutResponse
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class RemoteWorkoutDatasource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val userPreferences: UserPreferences,
) : WorkoutDatasource {

    private suspend fun getUserCollection(): String {
        val email = userPreferences.getEmail().first()
        return email + WORKOUTS_COLLECTION_SUFFIX
    }

    override fun getWorkouts(): Flow<List<WorkoutCardData>> {
        return getWorkoutResponses().map { workoutResponses ->
            organizeWorkouts(workoutResponses)
        }
    }

    override suspend fun addWorkoutToFirestore(documentId: String, dto: WorkoutDto) {
        val model = dto.copy(workoutId = documentId).toMap()
        val userCollection = getUserCollection()
        firebaseFirestore
            .collection(userCollection)
            .document(documentId)
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

    private fun getWorkoutResponses(): Flow<List<WorkoutResponse>> = flow {
        val userCollection = getUserCollection()
        firebaseFirestore
            .collection(userCollection)
            .orderBy(WORKOUT_DATE_FIELD, Query.Direction.ASCENDING)
            .snapshots()
            .map { querySnapshot ->
                querySnapshot.toObjects(WorkoutResponse::class.java)
            }.collect {
                emit(it)
            }
    }

    private fun organizeWorkouts(workoutResponses: List<WorkoutResponse>): List<WorkoutCardData> {
        return workoutResponses.map { workoutResponse ->
            workoutResponse.toDomain()
        }
    }
}

private const val WORKOUTS_COLLECTION_SUFFIX = "_workouts"
