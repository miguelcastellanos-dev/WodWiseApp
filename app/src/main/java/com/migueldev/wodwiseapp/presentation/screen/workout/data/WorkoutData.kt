package com.migueldev.wodwiseapp.presentation.screen.workout.data

import com.google.firebase.Timestamp

data class WorkoutData(
    val workoutId: String,
    val timeStamp: Timestamp,
    val session: String,
    val position: String,
    val exerciseType: String,
    val instructions: String,
)
