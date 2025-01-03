package com.migueldev.wodwiseapp.presentation.screen.user.signup

import androidx.compose.foundation.background
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
import com.migueldev.wodwiseapp.presentation.screen.user.composables.GoToLoginClickableText
import com.migueldev.wodwiseapp.presentation.screen.user.data.SignUpState

@Composable
fun Footer(
    signUpState: SignUpState,
    modifier: Modifier,
    navController: NavHostController,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        HorizontalDivider(
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primary)
                .height(Dimension.d1)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.size(Dimension.d24))
        GoToLoginClickableText(signUpState, navController)
        Spacer(modifier = Modifier.size(Dimension.d24))
    }
}
