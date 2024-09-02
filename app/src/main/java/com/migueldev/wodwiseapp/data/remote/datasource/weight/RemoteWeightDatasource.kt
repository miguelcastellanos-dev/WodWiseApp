package com.migueldev.wodwiseapp.data.remote.datasource.weight

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.snapshots
import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.data.mapper.toDomain
import com.migueldev.wodwiseapp.data.mapper.toMap
import com.migueldev.wodwiseapp.data.remote.response.WeightResponse
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.domain.usecase.GenerateWorkoutIdUseCase
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.tasks.await

class RemoteWeightDatasource @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    private val userPreferences: UserPreferences,
    private val generateWorkoutIdUseCase: GenerateWorkoutIdUseCase,
) : WeightsDatasource {

    private suspend fun getUserCollection(): String {
        val email = userPreferences.getEmail().first()
        return email + WEIGHTS_COLLECTION_SUFFIX
    }

    override fun getWeights(): Flow<List<WeightData>> {
        return getWeightResponses().map { weightResponses ->
            organizeWorkouts(weightResponses)
        }
    }

    override suspend fun addWeightToFirestore(weightDto: WeightDto) {
        val id = generateWorkoutIdUseCase()
        val model = weightDto.copy().toMap()
        val userCollection = getUserCollection()
        firebaseFirestore.collection(userCollection)
            .document(id)
            .set(model)
            .await()
    }

    override suspend fun removeWeight(id: String) {
        firebaseFirestore.collection(
            userPreferences.getEmail().first() +
                WEIGHTS_COLLECTION_SUFFIX
        ).document(id).delete()
    }

    private fun organizeWorkouts(
        weightResponses: List<WeightResponse>,
    ): List<WeightData> {
        return weightResponses.map { weightResponse ->
            weightResponse.toDomain()
        }
    }

    private fun getWeightResponses(): Flow<List<WeightResponse>> = flow {
        val userCollection = getUserCollection()
        firebaseFirestore
            .collection(userCollection)
            .snapshots()
            .map { querySnapshot ->
                querySnapshot.toObjects(WeightResponse::class.java)
            }.collect {
                emit(it)
            }
    }
}

private const val WEIGHTS_COLLECTION_SUFFIX = "_weights"
