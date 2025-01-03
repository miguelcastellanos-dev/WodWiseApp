package com.migueldev.wodwiseapp.domain.usecase

import arrow.core.Either
import com.google.firebase.firestore.FirebaseFirestoreException
import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.domain.exception.FirestoreAddDocumentException
import com.migueldev.wodwiseapp.domain.exception.FirestoreConnectionException
import com.migueldev.wodwiseapp.domain.exception.FirestoreUnknownErrorException
import com.migueldev.wodwiseapp.domain.repository.workout.WorkoutRepository
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutData
import javax.inject.Inject

class SaveWorkoutUseCase @Inject constructor(
    private val prepareTimestampUseCase: PrepareTimestampUseCase,
    private val generateWorkoutIdUseCase: GenerateWorkoutIdUseCase,
    private val validateInstructionsUseCase: ValidateInstructionsUseCase,
    private val workoutDtoUseCase: WorkoutDtoUseCase,
    private val workoutRepository: WorkoutRepository,
) {
    suspend operator fun invoke(
        instructions: String,
        session: String,
        position: String,
        exerciseType: String,
        dateMillis: Long?,
        notesInitialText: String,
    ): Either<Exception, WorkoutDto> {
        val timeStamp = prepareTimestampUseCase(dateMillis)
        val workoutId = generateWorkoutIdUseCase()
        val validInstructions = validateInstructionsUseCase(instructions)
        val workoutDto = workoutDtoUseCase(
            notesInitialText = notesInitialText,
            workoutData = WorkoutData(
                workoutId = workoutId,
                timeStamp = timeStamp,
                session = session,
                position = position,
                exerciseType = exerciseType,
                instructions = validInstructions
            )
        )
        return Either.catch {
            workoutRepository.addWorkoutToFirestore(
                documentId = workoutId,
                dto = workoutDto
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
