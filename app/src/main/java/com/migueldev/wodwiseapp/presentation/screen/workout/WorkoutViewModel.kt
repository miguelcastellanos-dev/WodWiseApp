package com.migueldev.wodwiseapp.presentation.screen.workout

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.usecase.SaveWorkoutUseCase
import com.migueldev.wodwiseapp.presentation.screen.workout.audio.AUDIO_INSTRUCTIONS_FILE
import com.migueldev.wodwiseapp.presentation.screen.workout.audio.AudioTranscriptionManager
import com.migueldev.wodwiseapp.presentation.screen.workout.audio.MediaRecorderWrapper
import com.migueldev.wodwiseapp.presentation.screen.workout.data.ExerciseTypeItem
import com.migueldev.wodwiseapp.presentation.screen.workout.data.PositionSessionItem
import com.migueldev.wodwiseapp.presentation.screen.workout.data.SessionItem
import com.migueldev.wodwiseapp.presentation.screen.workout.data.TextResourceProvider
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState
import dagger.hilt.android.lifecycle.HiltViewModel
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@HiltViewModel
class WorkoutViewModel @Inject constructor(
    private val audioTranscriptionManager: AudioTranscriptionManager,
    private val textResourceProvider: TextResourceProvider,
    private val saveWorkoutUseCase: SaveWorkoutUseCase,
    @IO private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _workoutState = MutableStateFlow(WorkoutState())
    val state: StateFlow<WorkoutState> = _workoutState

    private var mediaRecorder: MediaRecorderWrapper? = null
    private var audioFile: File? = null

    init {
        initializeTextResources()
    }

    private fun initializeTextResources() {
        _workoutState.value = textResourceProvider.initializeTextResources()
    }

    fun updateIsLoading(isLoading: Boolean) {
        _workoutState.update { it.copy(isLoading = isLoading) }
    }

    private fun updateIsRecording(isRecording: Boolean) {
        _workoutState.update { it.copy(isRecording = isRecording) }
    }

    fun updateIsDatePickerVisible(isDatePickerVisible: Boolean) {
        _workoutState.update { it.copy(isDatePickerVisible = isDatePickerVisible) }
    }

    fun updateInstructionsText(text: String) {
        _workoutState.update { state ->
            state.copy(instructionsStateText = text)
        }
    }

    fun updateSelectedSession(session: SessionItem) {
        _workoutState.update { state ->
            state.copy(sessionStateText = session)
        }
    }

    fun updateSelectedPosition(position: PositionSessionItem) {
        _workoutState.update { state ->
            state.copy(positionStateText = position)
        }
    }

    fun updateSelectedExerciseType(exerciseType: ExerciseTypeItem) {
        _workoutState.update { state ->
            state.copy(exerciseTypeStateText = exerciseType)
        }
    }

    fun resetCreateWorkoutState() {
        _workoutState.update { state ->
            state.copy(
                instructionsStateText = "",
                sessionStateText = state.sessionItems.first(),
                positionStateText = state.positionSessionItems.first(),
                exerciseTypeStateText = state.exerciseTypeItems.first()
            )
        }
    }

    private fun disableRecorderButtonTemporarily() {
        _workoutState.update { it.copy(isRecorderButtonEnabled = false) }
        viewModelScope.launch(ioDispatcher) {
            delay(DISABLE_RECORDER_BUTTON_TIME)
            _workoutState.update { it.copy(isRecorderButtonEnabled = true) }
        }
    }

    fun recordAudio(context: Context) {
        viewModelScope.launch(ioDispatcher) {
            disableRecorderButtonTemporarily()
            val currentState = _workoutState.value
            if (currentState.isRecording) {
                updateIsRecording(false)
                mediaRecorder?.stopRecord()
                audioTranscriptionManager.processAudioFileForTranscription(
                    this@WorkoutViewModel,
                    audioFile
                )
            } else {
                if (mediaRecorder == null) {
                    mediaRecorder = MediaRecorderWrapper(context)
                }

                File(context.cacheDir, AUDIO_INSTRUCTIONS_FILE).also {
                    mediaRecorder?.startRecord(it)
                    audioFile = it
                    updateIsRecording(true)
                }
            }
        }
    }

    suspend fun saveWorkout(
        instructions: String,
        session: String,
        position: String,
        exerciseType: String,
        dateMillis: Long?,
    ): Either<Exception, WorkoutDto> {
        return withContext(ioDispatcher) {
            saveWorkoutUseCase(
                workoutState = _workoutState.value,
                instructions = instructions,
                session = session,
                position = position,
                exerciseType = exerciseType,
                dateMillis = dateMillis
            )
        }
    }
}

private const val DISABLE_RECORDER_BUTTON_TIME = 1000L
