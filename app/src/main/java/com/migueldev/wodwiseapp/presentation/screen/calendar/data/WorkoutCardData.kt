package com.migueldev.wodwiseapp.presentation.screen.calendar.data

data class WorkoutCardData(
    var workoutId: String,
    var date: String,
    var session: String,
    var positionSession: String,
    var exerciseType: String,
    var instructions: String,
    var checkboxState: Boolean,
    var notes: String,
)
