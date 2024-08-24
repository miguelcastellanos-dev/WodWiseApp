package com.migueldev.wodwiseapp.data.mapper

import com.migueldev.wodwiseapp.data.remote.response.WorkoutResponse
import com.migueldev.wodwiseapp.domain.exception.TimeStampFirestoreException
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData

fun WorkoutResponse.toDomain(): WorkoutCardData {
    val date = timeStampToString(this.date).fold(
        { error ->
            throw TimeStampFirestoreException(error)
        },
        { formattedDate -> formattedDate }
    )
    return WorkoutCardData(
        workoutId = this.workoutId,
        date = date,
        session = this.session,
        positionSession = this.positionSession,
        exerciseType = this.exerciseType,
        instructions = this.instructions,
        checkboxState = this.checkboxState,
        notes = this.notes
    )
}
