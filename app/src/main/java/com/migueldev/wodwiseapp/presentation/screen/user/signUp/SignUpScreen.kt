package com.migueldev.wodwiseapp.presentation.screen.user.signUp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun SignUpScreen(signUpViewModel: SignUpViewModel, navController: NavHostController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.primaryContainer)
            .padding(Dimension.d16)
    ) {
        Header(Modifier.align(Alignment.TopEnd))
        Body(Modifier.align(Alignment.TopCenter), signUpViewModel)
        Footer(Modifier.align(Alignment.BottomCenter), navController)
    }
}