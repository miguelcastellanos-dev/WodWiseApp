package com.migueldev.wodwiseapp.data.dto

import com.google.firebase.Timestamp

data class WorkoutDto(
    val workoutId: String,
    val date: Timestamp,
    val session: String,
    val positionSession: String,
    val exerciseType: String,
    val instructions: String,
    val checkboxState: Boolean,
    val notes: String,
)
