package com.migueldev.wodwiseapp.data.remote.datasource.weight

import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.snapshots
import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.data.mapper.DATE_HISTORY_DATABASE_FIELD
import com.migueldev.wodwiseapp.data.mapper.ID_HISTORY_DATABASE_FIELD
import com.migueldev.wodwiseapp.data.mapper.REPETITIONS_HISTORY_LIST_DATABASE_FIELD
import com.migueldev.wodwiseapp.data.mapper.WEIGHT_HISTORY_DATABASE_FIELD
import com.migueldev.wodwiseapp.data.mapper.WEIGHT_HISTORY_LIST_DATABASE_FIELD
import com.migueldev.wodwiseapp.data.mapper.WEIGHT_REPETITION_MAXIMUM_DATABASE_FIELD
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

    override suspend fun addWeightToFirestore(documentId: String, weightDto: WeightDto) {
        val model = weightDto.copy().toMap()
        val userCollection = getUserCollection()
        firebaseFirestore.collection(userCollection)
            .document(documentId)
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
                querySnapshot.documents.map { documentSnapshot ->
                    val weightResponse = documentSnapshot.toObject(WeightResponse::class.java)
                    weightResponse
                }
            }.collect { weightResponses ->
                emit(weightResponses.filterNotNull())
            }
    }

    override suspend fun updateWeightRm(weightId: String, newRm: Double) {
        val userCollection = getUserCollection()
        firebaseFirestore.collection(userCollection)
            .document(weightId)
            .update(WEIGHT_REPETITION_MAXIMUM_DATABASE_FIELD, newRm)
            .await()
    }

    override suspend fun deleteWeightsCollection() {
        val userCollection = getUserCollection()
        val collectionRef = firebaseFirestore.collection(userCollection)
        val documents = collectionRef.get().await()

        for (document in documents) {
            document.reference.delete().await()
        }
    }

    override suspend fun addWeightHistoryToFirestore(
        weightId: String,
        weight: Double,
        repetitions: Int,
        date: String,
    ) {
        val userCollection = getUserCollection()
        val idHistory = generateWorkoutIdUseCase()
        val newHistory = mapOf(
            ID_HISTORY_DATABASE_FIELD to idHistory,
            WEIGHT_HISTORY_DATABASE_FIELD to weight,
            REPETITIONS_HISTORY_LIST_DATABASE_FIELD to repetitions,
            DATE_HISTORY_DATABASE_FIELD to date
        )

        firebaseFirestore.collection(userCollection)
            .document(weightId)
            .set(
                mapOf(WEIGHT_HISTORY_LIST_DATABASE_FIELD to FieldValue.arrayUnion(newHistory)),
                SetOptions.merge()
            )
            .await()
    }

    override suspend fun removeWeightHistory(weightId: String, idHistory: String) {
        val userCollection = getUserCollection()

        val documentSnapshot = firebaseFirestore
            .collection(userCollection)
            .document(weightId)
            .get()
            .await()

        if (documentSnapshot.exists()) {
            val weightHistoryList = documentSnapshot
                .get(WEIGHT_HISTORY_LIST_DATABASE_FIELD) as? List<*>

            val validHistoryList = weightHistoryList?.filterIsInstance<Map<String, Any>>()

            val updatedHistoryList = validHistoryList?.filterNot { history ->
                history[ID_HISTORY_DATABASE_FIELD] == idHistory
            }

            if (updatedHistoryList != null) {
                firebaseFirestore.collection(userCollection)
                    .document(weightId)
                    .update(WEIGHT_HISTORY_LIST_DATABASE_FIELD, updatedHistoryList)
                    .await()
            }
        }
    }
}

private const val WEIGHTS_COLLECTION_SUFFIX = "_weights"
