package com.migueldev.wodwiseapp.model

sealed class Routes(val route: String) {
    data object LoginScreen : Routes("loginScreen")
    data object SignUpScreen : Routes("signUpScreen")
    data object ScaffoldScreen : Routes("scaffoldScreen")
    data object CalendarScreen : Routes("calendarScreen")
    data object WorkoutScreen : Routes("workoutScreen")
    data object WeightScreen : Routes("weightScreen")
    data object CoachScreen : Routes("coachScreen")
    data object WeightDetailScreen : Routes("weightDetailScreen/{weightId}") {
        fun createRoute(weightId: String): String {
            return "weightDetailScreen/$weightId"
        }
    }
    data object CalendarDetailScreen :
        Routes(
            "calendarDetailView/" +
                "{${ConstCalendarDetail.POSITION_SESSION}}/" +
                "{${ConstCalendarDetail.EXERCISE}}/" +
                "{${ConstCalendarDetail.INSTRUCTIONS}}/" +
                "{${ConstCalendarDetail.WORKOUT_ID}}/" +
                "{${ConstCalendarDetail.DATE}}/" +
                "{${ConstCalendarDetail.SESSION}}/" +
                "{${ConstCalendarDetail.NOTES}}/"
        ) {
        fun createRoute(
            params: CreateRouteCalendarDetailParams,
        ): String {
            return "calendarDetailView/" +
                "${params.positionSession}/" +
                "${params.exercise}/" +
                "${params.instructions}/" +
                "${params.workoutId}/" +
                "${params.date}/" +
                "${params.session}/" +
                "${params.notes}/"
        }
    }
    data object SettingScreen : Routes("settingScreen")
    data object ProfileScreen : Routes("profileScreen")
}

object ConstCalendarDetail {
    const val POSITION_SESSION = "positionSession"
    const val EXERCISE = "exercise"
    const val INSTRUCTIONS = "instructions"
    const val WORKOUT_ID = "workoutId"
    const val DATE = "date"
    const val SESSION = "session"
    const val NOTES = "notes"
}

data class CreateRouteCalendarDetailParams(
    val positionSession: String,
    val exercise: String,
    val instructions: String,
    val workoutId: String,
    val date: String,
    val session: String,
    val notes: String,
)
