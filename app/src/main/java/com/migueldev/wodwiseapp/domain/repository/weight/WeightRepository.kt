package com.migueldev.wodwiseapp.domain.repository.weight

import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.data.remote.datasource.weight.WeightsDatasource
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class WeightRepository @Inject constructor(
    private val weightDatasource: WeightsDatasource,
) {
    fun getWeights(): Flow<List<WeightData>> {
        return weightDatasource.getWeights()
    }

    suspend fun addWeightToFirestore(documentId: String, weightDto: WeightDto) {
        return weightDatasource.addWeightToFirestore(documentId, weightDto)
    }

    suspend fun removeWeight(id: String) {
        return weightDatasource.removeWeight(id)
    }

    suspend fun updateWeightRm(weightId: String, newRm: Double) {
        return weightDatasource.updateWeightRm(weightId, newRm)
    }
}
