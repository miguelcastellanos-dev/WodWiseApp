package com.migueldev.wodwiseapp.presentation

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.navigation.compose.rememberNavController
import com.migueldev.wodwiseapp.presentation.navigation.AppNavigation
import com.migueldev.wodwiseapp.presentation.screen.scaffold.ScaffoldViewModel
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme
import com.migueldev.wodwiseapp.presentation.screen.user.login.LoginViewModel
import com.migueldev.wodwiseapp.presentation.screen.user.signup.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var appStateManager: AppStateManager

    private val loginViewModel: LoginViewModel by viewModels()
    private val signUpViewModel: SignUpViewModel by viewModels()
    private val scaffoldViewModel: ScaffoldViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val loginState by loginViewModel.loginState.collectAsState()
            val signUpState by signUpViewModel.signUpState.collectAsState()
            val scaffoldState by scaffoldViewModel.state.collectAsState()
            val navController = rememberNavController()
            val isEmailLoading = remember { mutableStateOf(true) }
            val userEmail = remember { mutableStateOf<String?>(null) }

            appStateManager.ObserveUserEmail(isEmailLoading, userEmail)

            WodWiseAppTheme {
                val backgroundColor = MaterialTheme.colorScheme.background
                setSystemBarsColor(window, backgroundColor)

                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding()
                ) {
                    val viewModelGroup = ViewModelGroup(
                        loginViewModel = loginViewModel,
                        signUpViewModel = signUpViewModel,
                        scaffoldViewModel = scaffoldViewModel
                    )
                    val stateGroup = StateGroup(
                        loginState = loginState,
                        signUpState = signUpState,
                        scaffoldState = scaffoldState

                    )
                    val appState = appStateManager.initializeAppState(
                        viewModelGroup = viewModelGroup,
                        stateGroup = stateGroup,
                        navController = navController,
                        startDestination = userEmail.value != null
                    )
                    AppNavigation(appState = appState)
                }
            }
        }
    }

    private fun setSystemBarsColor(window: Window, backgroundColor: Color) {
        window.statusBarColor = backgroundColor.toArgb()
        window.navigationBarColor = backgroundColor.toArgb()

        val insetsController = WindowInsetsControllerCompat(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = false
        insetsController.isAppearanceLightNavigationBars = true
    }
}
