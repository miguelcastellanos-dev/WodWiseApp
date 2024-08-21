package com.migueldev.wodwiseapp.presentation.screen.user.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState

@Composable
fun SignUpScreen(
    loginState: LoginState,
    signUpState: SignUpState,
    signUpViewModel: SignUpViewModel,
    navController: NavHostController,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(Dimension.d16)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        BodySignUpScreen(
            loginState = loginState,
            modifier = Modifier.align(Alignment.TopCenter),
            signUpViewModel = signUpViewModel,
            navController = navController,
            context = context
        )
        Footer(
            signUpState = signUpState,
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )
    }
}
