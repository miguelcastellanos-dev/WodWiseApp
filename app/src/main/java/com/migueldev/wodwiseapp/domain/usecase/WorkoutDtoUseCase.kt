package com.migueldev.wodwiseapp.domain.usecase

import com.migueldev.wodwiseapp.data.dto.WorkoutDto
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutData
import javax.inject.Inject

class WorkoutDtoUseCase @Inject constructor() {
    operator fun invoke(
        notesInitialText: String,
        workoutData: WorkoutData,
    ): WorkoutDto {
        return WorkoutDto(
            workoutId = workoutData.workoutId,
            date = workoutData.timeStamp,
            session = workoutData.session,
            positionSession = workoutData.position,
            exerciseType = workoutData.exerciseType,
            instructions = workoutData.instructions,
            checkboxState = false,
            notes = notesInitialText
        )
    }
}
