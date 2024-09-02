package com.migueldev.wodwiseapp.model

sealed class Routes(val route: String) {
    data object LoginScreen : Routes("loginScreen")
    data object SignUpScreen : Routes("signUpScreen")
    data object ScaffoldScreen : Routes("scaffoldScreen")
    data object CalendarScreen : Routes("calendarScreen")
    data object WorkoutScreen : Routes("workoutScreen")
    data object WeightScreen : Routes("weightScreen")
    data object CoachScreen : Routes("coachScreen")
}
