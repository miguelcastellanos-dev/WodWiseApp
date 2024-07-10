package com.migueldev.wodwiseapp.presentation.screen.user.composables

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.migueldev.wodwiseapp.R

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.logo_fondo_naranja),
        contentDescription = stringResource(id = R.string.description_logo_app),
        contentScale = ContentScale.FillWidth,
        modifier = modifier
    )
}
