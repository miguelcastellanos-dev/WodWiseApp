package com.migueldev.wodwiseapp.presentation.screen.weightdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.exception.AddWeightHistoryException
import com.migueldev.wodwiseapp.domain.exception.RemoveWeightHistoryException
import com.migueldev.wodwiseapp.domain.repository.weight.WeightRepository
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailTextResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class WeightDetailViewModel @Inject constructor(
    private val weightRepository: WeightRepository,
    private val weightDetailTextResourceProvider: WeightDetailTextResourceProvider,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _weightDetailStates = MutableStateFlow(WeightDetailState())
    val state: StateFlow<WeightDetailState> = _weightDetailStates

    init {
        initializeTextResources()
    }

    fun initializeTextResources() {
        _weightDetailStates.value = weightDetailTextResourceProvider.initializeTextResources()
    }

    private suspend fun updateWeightRm(weightId: String, newRm: Double) {
        return weightRepository.updateWeightRm(weightId, newRm)
    }

    fun updateRmInCoroutine(weightId: String, newRm: Double) {
        viewModelScope.launch(ioDispatcher) {
            updateWeightRm(weightId, newRm)
        }
    }

    fun addWeightHistory(
        weightId: String,
        weight: Double,
        repetitions: Int,
        date: String,
    ) {
        viewModelScope.launch {
            try {
                weightRepository.addWeightHistoryToFirestore(
                    weightId,
                    weight,
                    repetitions,
                    date
                )
            } catch (e: AddWeightHistoryException) {
                throw AddWeightHistoryException(weightId, e)
            }
        }
    }

    fun removeWeightHistory(weightId: String, idHistory: String) {
        viewModelScope.launch {
            try {
                weightRepository.removeWeightHistory(
                    weightId,
                    idHistory
                )
            } catch (e: RemoveWeightHistoryException) {
                throw RemoveWeightHistoryException(weightId, idHistory, e)
            }
        }
    }
}
