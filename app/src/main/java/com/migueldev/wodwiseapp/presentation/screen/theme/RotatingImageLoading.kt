package com.migueldev.wodwiseapp.presentation.screen.theme

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp

@Composable
fun RotatingImageLoading(
    imageResource: Int,
    modifier: Modifier = Modifier,
    size: Dp,
) {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")

    val rotationAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotationAngle"
    )

    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = modifier
            .size(size)
            .graphicsLayer { rotationZ = rotationAngle }
    )
}
