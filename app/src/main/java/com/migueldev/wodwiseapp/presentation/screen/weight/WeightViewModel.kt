package com.migueldev.wodwiseapp.presentation.screen.weight

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.firebase.firestore.FirebaseFirestoreException
import com.migueldev.wodwiseapp.data.dto.WeightDto
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.exception.FirestoreAddDocumentException
import com.migueldev.wodwiseapp.domain.exception.FirestoreConnectionException
import com.migueldev.wodwiseapp.domain.exception.FirestoreUnknownErrorException
import com.migueldev.wodwiseapp.domain.repository.weight.WeightRepository
import com.migueldev.wodwiseapp.domain.usecase.GenerateWorkoutIdUseCase
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightTextResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.weight.data.WeightsState
import com.migueldev.wodwiseapp.presentation.screen.weight.data.createWeightDto
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class WeightViewModel @Inject constructor(
    private val weightRepository: WeightRepository,
    private val generateWorkoutIdUseCase: GenerateWorkoutIdUseCase,
    private val weightTextResourceProvider: WeightTextResourceProvider,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _weightsStates = MutableStateFlow(WeightsState())
    val state: StateFlow<WeightsState> = _weightsStates

    init {
        initializeTextResources()
        getWeightsFromDatabase()
    }

    private fun initializeTextResources() {
        _weightsStates.value = weightTextResourceProvider.weightInitializeTextResources()
    }

    fun getWeightsFromDatabase() {
        viewModelScope.launch(ioDispatcher) {
            _weightsStates.value = _weightsStates.value.copy(isLoading = true)
            weightRepository.getWeights().collect { exercisesWeightList ->
                _weightsStates.update {
                    it.copy(
                        exercisesWeightList = exercisesWeightList,
                        isLoading = false
                    )
                }
            }
        }
    }

    suspend fun saveWeight(
        nameExercise: String,
        rm: Double,
    ): Either<Exception, WeightDto> {
        val weightId = generateWorkoutIdUseCase()
        val weightDto = createWeightDto(
            weightId = weightId,
            nameExercise = nameExercise,
            rm = rm
        )
        return Either.catch {
            weightRepository.addWeightToFirestore(weightDto)
            weightDto
        }.mapLeft { e ->
            when (e) {
                is FirebaseFirestoreException -> {
                    if (e.message?.contains("UNAVAILABLE") == true ||
                        e.message?.contains("NETWORK_ERROR") == true
                    ) {
                        FirestoreConnectionException(e)
                    } else {
                        FirestoreAddDocumentException(e)
                    }
                }

                else -> FirestoreUnknownErrorException(e)
            }
        }
    }

    suspend fun removeWeight(weightId: String) {
        weightRepository.removeWeight(weightId)
    }
}
