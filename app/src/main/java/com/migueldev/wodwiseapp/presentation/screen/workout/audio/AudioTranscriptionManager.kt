package com.migueldev.wodwiseapp.presentation.screen.workout.audio

import com.aallam.openai.api.audio.TranscriptionRequest
import com.aallam.openai.api.file.FileSource
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import com.migueldev.wodwiseapp.di.IO
import com.migueldev.wodwiseapp.domain.usecase.HandleResultIAUseCase
import com.migueldev.wodwiseapp.domain.usecase.RequestChatCompletionIAUseCase
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutViewModel
import java.io.File
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import okio.source

class AudioTranscriptionManager @Inject constructor(
    private val openAI: OpenAI,
    @IO private val ioDispatcher: CoroutineDispatcher,
    private val requestChatCompletionIAUseCase: RequestChatCompletionIAUseCase,
    private val handleResultIAUseCase: HandleResultIAUseCase,
) {

    suspend fun processAudioFileForTranscription(
        workoutViewModel: WorkoutViewModel,
        file: File?,
    ) =
        withContext(ioDispatcher) {
            file?.source()?.let {
                workoutViewModel.updateIsLoading(true)

                val transcriptionRequest = TranscriptionRequest(
                    audio = FileSource(name = AUDIO_INSTRUCTIONS_FILE, source = it),
                    model = ModelId(WHISPER_MODEL)
                )
                val transcription = openAI.transcription(transcriptionRequest)
                processTranscriptionResult(workoutViewModel, transcription.text)

                workoutViewModel.updateIsLoading(false)
            }
        }

    private suspend fun processTranscriptionResult(
        workoutViewModel: WorkoutViewModel,
        userInfo: String,
    ) =
        withContext(ioDispatcher) {
            workoutViewModel.updateIsLoading(true)

            val systemInfo = workoutViewModel.state.value.trainingInfoSystemInstructionsText
            val result = requestChatCompletionIAUseCase(
                userInfo = userInfo,
                systemInfo = systemInfo
            )
            handleResultIAUseCase(result) { response ->
                workoutViewModel.updateInstructionsText(response ?: "Empty instructions")
            }
            workoutViewModel.updateIsLoading(false)
        }
}

const val AUDIO_INSTRUCTIONS_FILE = "audioInstructions.mp3"
private const val WHISPER_MODEL = "whisper-1"
