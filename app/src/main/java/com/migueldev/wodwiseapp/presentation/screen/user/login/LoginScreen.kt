package com.migueldev.wodwiseapp.presentation.screen.user.login

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
import com.migueldev.wodwiseapp.presentation.framework.ToastWrapper
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun LoginScreen(
    loginViewModel: LoginViewModel,
    navController: NavHostController,
    toastWrapper: ToastWrapper,
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(Dimension.d16)
    ) {
        Header(
            modifier = Modifier.align(Alignment.TopEnd)
        )
        Body(
            modifier = Modifier.align(Alignment.TopCenter),
            loginViewModel = loginViewModel,
            context = context,
            toastWrapper = toastWrapper
        )
        Footer(
            modifier = Modifier.align(Alignment.BottomCenter),
            navController = navController
        )
    }
}
