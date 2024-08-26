package com.migueldev.wodwiseapp.presentation.screen.coach

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.google.firebase.Timestamp
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.usecase.CreateTrainingInfoUseCase
import com.migueldev.wodwiseapp.domain.usecase.HandleResultIAUseCase
import com.migueldev.wodwiseapp.domain.usecase.RequestChatCompletionIAUseCase
import com.migueldev.wodwiseapp.domain.usecase.SaveWorkoutUseCase
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachTextResourceProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class CoachViewModel @Inject constructor(
    private val coachTextResourceProvider: CoachTextResourceProvider,
    private val handleResultIAUseCase: HandleResultIAUseCase,
    private val requestChatCompletionIAUseCase: RequestChatCompletionIAUseCase,
    private val saveWorkoutUseCase: SaveWorkoutUseCase,
    private val createTrainingInfoUseCase: CreateTrainingInfoUseCase,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _coachState = MutableStateFlow(CoachState())
    val state: StateFlow<CoachState> = _coachState

    init {
        initializeTextResources()
    }

    private fun initializeTextResources() {
        _coachState.value = coachTextResourceProvider.coachInitializeTextResources()
    }

    fun onFormatNavigationClicked(isNext: Boolean) {
        val currentState = _coachState.value
        val currentIndex = currentState.formatListTexts.indexOf(currentState.selectedFormat)

        val newIndex = if (isNext) {
            (currentIndex + 1) % currentState.formatListTexts.size
        } else {
            (
                currentIndex - 1 + currentState.formatListTexts.size
                ) % currentState.formatListTexts.size
        }
        updateSelectedFormat(currentState.formatListTexts[newIndex])
    }

    fun createInfo(
        selectedTime: Int,
        selectedFormat: String,
        selectedExerciseLists: Set<String>,
    ) =
        viewModelScope.launch(ioDispatcher) {
            _coachState.update { it.copy(isLoading = true) }

            val userInfo = createTrainingInfoUseCase(
                selectedTime = selectedTime,
                selectedFormat = selectedFormat,
                selectedExerciseLists = selectedExerciseLists
            )
            val systemInfo = _coachState.value.systemInfoText
            val result = requestChatCompletionIAUseCase(
                userInfo = userInfo,
                systemInfo = systemInfo
            )
            handleResultIAUseCase(result) { response ->
                _coachState.update { it.copy(answerOpenAi = response) }
            }
            _coachState.update { it.copy(isLoading = false) }
        }

    fun updateSelectedTime(selectedTime: Int) {
        _coachState.update { it.copy(selectedTime = selectedTime) }
    }

    fun updateSelectedFormat(selectedFormat: String) {
        _coachState.update { it.copy(selectedFormat = selectedFormat) }
    }

    fun onExerciseSelected(exerciseName: String, selected: Boolean) {
        val updatedExercises = if (selected) {
            _coachState.value.selectedExerciseLists + exerciseName
        } else {
            _coachState.value.selectedExerciseLists - exerciseName
        }
        _coachState.update { it.copy(selectedExerciseLists = updatedExercises) }

        val updatedBarbellMovementsStates =
            _coachState.value.barbellMovementsCoachExerciseData.map {
                if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
            }
        val updatedBodyWeightMovementsStates =
            _coachState.value.bodyWeightCoachExerciseData.map {
                if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
            }
        val updatedCardioStates =
            _coachState.value.metabolicCoachExerciseData.map {
                if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
            }
        val updatedDumbbellStates =
            _coachState.value.dumbbellCoachExerciseData.map {
                if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
            }
        val updatedFunctionalWeightedStates =
            _coachState.value.functionalWeightedCoachExerciseData.map {
                if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
            }
        val updatedGymnasticStates = _coachState.value.gymnasticCoachExerciseData.map {
            if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
        }
        val updatedKettlebellStates = _coachState.value.kettlebellCoachExerciseData.map {
            if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
        }
        val updatedStrengthStates = _coachState.value.strengthCoachExerciseData.map {
            if (it.exerciseName == exerciseName) it.copy(isSelected = selected) else it
        }

        _coachState.update {
            it.copy(
                barbellMovementsCoachExerciseData = updatedBarbellMovementsStates,
                bodyWeightCoachExerciseData = updatedBodyWeightMovementsStates,
                metabolicCoachExerciseData = updatedCardioStates,
                dumbbellCoachExerciseData = updatedDumbbellStates,
                functionalWeightedCoachExerciseData = updatedFunctionalWeightedStates,
                gymnasticCoachExerciseData = updatedGymnasticStates,
                kettlebellCoachExerciseData = updatedKettlebellStates,
                strengthCoachExerciseData = updatedStrengthStates
            )
        }
    }

    fun handleDialogState(shouldShow: Boolean) {
        _coachState.update { currentState ->
            val newShowDialogState = mutableStateOf(
                shouldShow && currentState.answerOpenAi != null
            )
            currentState.copy(
                showResponseDialog = newShowDialogState,
                answerOpenAi = if (!shouldShow) null else currentState.answerOpenAi
            )
        }
    }

    fun showDialog(): Boolean {
        val state = _coachState.value
        return state.showResponseDialog.value && state.answerOpenAi != null
    }

    suspend fun saveIntelligenceWorkout(
        instructions: String,
    ): Either<Exception, WorkoutDto> {
        val dateMillis = Timestamp.now().toDate().time
        val session = _coachState.value.sessionText
        val position = _coachState.value.positionText
        val exerciseType = _coachState.value.exerciseTypeText
        return withContext(ioDispatcher) {
            saveWorkoutUseCase(
                toast = _coachState.value.savedWorkoutToastText,
                instructions = instructions,
                session = session,
                position = position,
                exerciseType = exerciseType,
                dateMillis = dateMillis,
                notesInitialText = _coachState.value.notesInitialText
            )
        }
    }

    fun foldOrUnfoldExerciseTab(exerciseHeaderText: String) {
        _coachState.update { currentState ->
            val updatedMap = currentState.showExerciseCardState.toMutableMap()
            val isCurrentlyVisible = updatedMap[exerciseHeaderText] ?: false
            updatedMap[exerciseHeaderText] = !isCurrentlyVisible
            currentState.copy(showExerciseCardState = updatedMap)
        }
    }
}
