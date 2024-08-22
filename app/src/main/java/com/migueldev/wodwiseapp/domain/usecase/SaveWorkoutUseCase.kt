package com.migueldev.wodwiseapp.domain.usecase

import arrow.core.Either
import com.google.firebase.firestore.FirebaseFirestoreException
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.domain.exception.FirestoreAddDocumentException
import com.migueldev.wodwiseapp.domain.exception.FirestoreConnectionException
import com.migueldev.wodwiseapp.domain.exception.FirestoreUnknownErrorException
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutData
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState
import javax.inject.Inject

class SaveWorkoutUseCase @Inject constructor(
    private val prepareTimestampUseCase: PrepareTimestampUseCase,
    private val generateWorkoutIdUseCase: GenerateWorkoutIdUseCase,
    private val validateInstructionsUseCase: ValidateInstructionsUseCase,
    private val workoutDtoUseCase: WorkoutDtoUseCase,
    private val workoutRepository: WorkoutRepository,
    private val toastWrapper: ToastWrapper,
) {
    suspend operator fun invoke(
        workoutState: WorkoutState,
        instructions: String,
        session: String,
        position: String,
        exerciseType: String,
        dateMillis: Long?,
    ): Either<Exception, WorkoutDto> {
        val timeStamp = prepareTimestampUseCase(dateMillis)
        val workoutId = generateWorkoutIdUseCase()
        val validInstructions = validateInstructionsUseCase(instructions)
        val workoutDto = workoutDtoUseCase(
            WorkoutData(
                workoutId = workoutId,
                timeStamp = timeStamp,
                session = session,
                position = position,
                exerciseType = exerciseType,
                instructions = validInstructions
            )
        )
        return Either.catch {
            workoutRepository.addWorkoutToFirestore(workoutDto)
            toastWrapper.show(
                workoutState.savedWorkoutToastText
            )
            workoutDto
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
}
