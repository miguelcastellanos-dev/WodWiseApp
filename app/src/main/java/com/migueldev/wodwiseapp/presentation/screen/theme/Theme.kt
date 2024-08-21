package com.migueldev.wodwiseapp.presentation.screen.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = DarkOrange,
    onPrimary = Black,
    background = Black,
    onBackground = White,
    primaryContainer = DarkGray,
    onPrimaryContainer = White,
    secondaryContainer = MediumGray
)

private val LightColorScheme = lightColorScheme(
    primary = DarkOrange,
    onPrimary = White,
    background = White,
    onBackground = Black,
    primaryContainer = LightWhite,
    onPrimaryContainer = Black,
    secondaryContainer = MediumGray
)

@Composable
fun WodWiseAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
