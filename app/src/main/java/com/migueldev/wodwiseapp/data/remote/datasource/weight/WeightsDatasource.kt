package com.migueldev.wodwiseapp.data.remote.datasource.weight

import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightData
import kotlinx.coroutines.flow.Flow

interface WeightsDatasource {
    fun getWeights(): Flow<List<WeightData>>

    suspend fun addWeightToFirestore(
        documentId: String,
        weightDto: WeightDto,
    )

    suspend fun removeWeight(id: String)

    suspend fun updateWeightRm(weightId: String, newRm: Double)
}
