package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.screen.user.data.LoginState

@Composable
fun ImageLogo(loginState: LoginState, modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_app),
        contentDescription = loginState.descriptionLogoApp,
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}
