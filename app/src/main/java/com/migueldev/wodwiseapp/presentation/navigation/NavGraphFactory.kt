package com.migueldev.wodwiseapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.migueldev.wodwiseapp.model.ConstCalendarDetail
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.calendar.CalendarScreenContent
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.calendardetail.CalendarDetailScreen
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachScreenContent
import com.migueldev.wodwiseapp.presentation.screen.coach.CoachViewModel
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachActionState
import com.migueldev.wodwiseapp.presentation.screen.coach.data.CoachState
import com.migueldev.wodwiseapp.presentation.screen.profile.ProfileScreen
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.ScaffoldScreenContent
import com.migueldev.wodwiseapp.presentation.screen.setting.SettingScreen
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpScreen
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel
import com.migueldev.wodwiseapp.presentation.screen.weight.WeightScreenContent
import com.migueldev.wodwiseapp.presentation.screen.weight.WeightViewModel
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.WeightDetailScreen
import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightDetailState
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutScreenContent
import com.migueldev.wodwiseapp.presentation.screen.workout.WorkoutViewModel
import com.migueldev.wodwiseapp.presentation.screen.workout.data.WorkoutState

fun NavGraphBuilder.loginScreen(
    appState: AppState,
    appActionState: AppActionState,
) {
    composable(Routes.LoginScreen.route) {
        LoginScreen(
            appState = appState,
            onSendPasswordResetEmailClicked = appActionState.onSendPasswordResetEmailClicked
        )
    }
}

fun NavGraphBuilder.signUpScreen(
    loginState: LoginState,
    signUpState: SignUpState,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
) {
    composable(Routes.SignUpScreen.route) {
        SignUpScreen(
            loginState = loginState,
            signUpState = signUpState,
            signUpViewModel = signUpViewModel,
            navController = navController
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.scaffoldScreen(
    appState: AppState,
    appActionState: AppActionState,
    coachActionState: CoachActionState,
) {
    composable(Routes.ScaffoldScreen.route) {
        ScaffoldScreenContent(
            appState = appState,
            appActionState = appActionState,
            coachActionState = coachActionState
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.addWorkoutScreen(
    workoutViewModel: WorkoutViewModel,
    workoutState: WorkoutState,
    datePickerState: DatePickerState,
    appActionState: AppActionState,
) {
    composable(Routes.WorkoutScreen.route) {
        WorkoutScreenContent(
            workoutViewModel = workoutViewModel,
            workoutState = workoutState,
            datePickerState = datePickerState,
            appActionState = appActionState
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.calendarScreen(
    appState: AppState,
    appActionState: AppActionState,
) {
    composable(Routes.CalendarScreen.route) {
        CalendarScreenContent(
            appState = appState,
            appActionState = appActionState
        )
    }
}

fun NavGraphBuilder.coachScreen(
    coachState: CoachState,
    intelligenceViewModel: CoachViewModel,
    coachActionState: CoachActionState,
) {
    composable(Routes.CoachScreen.route) {
        CoachScreenContent(
            coachState = coachState,
            coachViewModel = intelligenceViewModel,
            coachActionState = coachActionState
        )
    }
}

fun NavGraphBuilder.weightScreen(
    weightViewModel: WeightViewModel,
    appActionState: AppActionState,
) {
    composable(Routes.WeightScreen.route) {
        WeightScreenContent(
            weightViewModel = weightViewModel,
            appActionState = appActionState
        )
    }
}

fun NavGraphBuilder.weightDetailScreen(
    weightViewModel: WeightViewModel,
    weightDetailState: WeightDetailState,
    appActionState: AppActionState,
) {
    composable(
        route = Routes.WeightDetailScreen.route,
        arguments = listOf(
            navArgument("weightId") { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val weightId = backStackEntry.arguments?.getString(
            "weightId"
        ) ?: "default_weight_id"
        WeightDetailScreen(
            weightViewModel = weightViewModel,
            weightDetailState = weightDetailState,
            weightId = weightId,
            appActionState = appActionState
        )
    }
}

fun NavGraphBuilder.calendarDetailScreen(
    appState: AppState,
    appActionState: AppActionState,
) {
    composable(
        route = Routes.CalendarDetailScreen.route,
        arguments = listOf(
            navArgument(ConstCalendarDetail.POSITION_SESSION) { type = NavType.StringType },
            navArgument(ConstCalendarDetail.EXERCISE) { type = NavType.StringType },
            navArgument(ConstCalendarDetail.INSTRUCTIONS) { type = NavType.StringType },
            navArgument(ConstCalendarDetail.WORKOUT_ID) { type = NavType.StringType },
            navArgument(ConstCalendarDetail.DATE) { type = NavType.StringType },
            navArgument(ConstCalendarDetail.SESSION) { type = NavType.StringType },
            navArgument(ConstCalendarDetail.NOTES) { type = NavType.StringType }
        )
    ) { backStackEntry ->
        val positionSession =
            backStackEntry.arguments?.getString(
                ConstCalendarDetail.POSITION_SESSION
            ).orEmpty()
        val exercise = backStackEntry.arguments?.getString(
            ConstCalendarDetail.EXERCISE
        ).orEmpty()
        val encodedInstructions =
            backStackEntry.arguments?.getString(
                ConstCalendarDetail.INSTRUCTIONS
            ).orEmpty()
        val workoutId = backStackEntry.arguments?.getString(
            ConstCalendarDetail.WORKOUT_ID
        ).orEmpty()
        val date = backStackEntry.arguments?.getString(
            ConstCalendarDetail.DATE
        ).orEmpty()
        val session = backStackEntry.arguments?.getString(
            ConstCalendarDetail.SESSION
        ).orEmpty()
        val notes = backStackEntry.arguments?.getString(
            ConstCalendarDetail.NOTES
        ).orEmpty()

        val workoutCardData = WorkoutCardData(
            workoutId = workoutId,
            date = date,
            session = session,
            positionSession = positionSession,
            exerciseType = exercise,
            instructions = encodedInstructions,
            checkboxState = false,
            notes = notes
        )
        CalendarDetailScreen(
            appState = appState,
            workoutCardData = workoutCardData,
            onCheckboxClicked = appActionState.onCheckboxClicked,
            onInstructionIconClicked = appActionState.onInstructionIconClicked,
            onNotesIconClicked = appActionState.onNotesIconClicked
        )
    }
}

fun NavGraphBuilder.settingScreen(
    settingState: SettingState,
    appActionState: AppActionState,
) {
    composable(Routes.SettingScreen.route) {
        SettingScreen(
            settingState = settingState,
            onThemeSwitched = appActionState.onThemeSwitched,
            onBackClicked = appActionState.onBackClicked
        )
    }
}

fun NavGraphBuilder.profileScreen(
    settingState: SettingState,
    profileState: ProfileState,
    appActionState: AppActionState,
) {
    composable(Routes.ProfileScreen.route) {
        ProfileScreen(
            settingState = settingState,
            profileState = profileState,
            onBackClicked = appActionState.onBackClicked,
            onLogoutClicked = appActionState.onLogoutClicked,
            onDeleteUserClicked = appActionState.onDeleteUserClicked,
            onDeleteAllWorkoutsClicked = appActionState.onDeleteAllWorkoutsClicked,
            onDeleteAllWeightsClicked = appActionState.onDeleteAllWeightsClicked
        )
    }
}
