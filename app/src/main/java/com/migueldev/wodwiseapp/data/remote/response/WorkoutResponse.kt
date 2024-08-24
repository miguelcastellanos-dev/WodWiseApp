package com.migueldev.wodwiseapp.data.remote.response

import com.google.firebase.Timestamp

data class WorkoutResponse(
    val workoutId: String = "",
    val date: Timestamp = Timestamp(0, 0),
    val session: String = "",
    val positionSession: String = "",
    val exerciseType: String = "",
    val instructions: String = "",
    val checkboxState: Boolean = false,
    val notes: String = "",
)
