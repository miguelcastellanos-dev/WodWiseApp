package com.migueldev.wodwiseapp.presentation.screen.user.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.user.composables.GoToSignUp
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun Footer(
    loginState: LoginState,
    modifier: Modifier,
    navController: NavHostController,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .height(Dimension.d1)
        )
        Spacer(modifier = Modifier.size(Dimension.d24))
        GoToSignUp(loginState = loginState, navController = navController)
        Spacer(modifier = Modifier.size(Dimension.d24))
    }
}
