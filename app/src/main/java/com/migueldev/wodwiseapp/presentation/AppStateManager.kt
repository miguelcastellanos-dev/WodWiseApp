package com.migueldev.wodwiseapp.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.data.session.UserPreferences
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.ScaffoldViewModel
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppStateManager @Inject constructor(
    private val userPreferences: UserPreferences,
) {

    @Composable
    fun initializeAppState(
        viewModelGroup: ViewModelGroup,
        stateGroup: StateGroup,
        navController: NavHostController,
        startDestination: Boolean,
    ): AppState {
        return AppState(
            loginViewModel = viewModelGroup.loginViewModel,
            signUpViewModel = viewModelGroup.signUpViewModel,
            scaffoldViewModel = viewModelGroup.scaffoldViewModel,
            scaffoldState = stateGroup.scaffoldState,
            loginState = stateGroup.loginState,
            signUpState = stateGroup.signUpState,
            navController = navController,
            userPreferences = userPreferences,
            startDestination = determineStartDestination(startDestination)
        )
    }

    private fun determineStartDestination(isLoggedIn: Boolean): String {
        return if (isLoggedIn) Routes.ScaffoldScreen.route else Routes.LoginScreen.route
    }

    @Composable
    fun ObserveUserEmail(
        isEmailLoading: MutableState<Boolean>,
        userEmail: MutableState<String?>,
    ) {
        LaunchedEffect(Unit) {
            userPreferences.getEmail().collect { email ->
                userEmail.value = email
                isEmailLoading.value = false
            }
        }
    }
}

data class ViewModelGroup(
    val loginViewModel: LoginViewModel,
    val signUpViewModel: SignUpViewModel,
    val scaffoldViewModel: ScaffoldViewModel,
)

data class StateGroup(
    val loginState: LoginState,
    val signUpState: SignUpState,
    val scaffoldState: ScaffoldState,
)
