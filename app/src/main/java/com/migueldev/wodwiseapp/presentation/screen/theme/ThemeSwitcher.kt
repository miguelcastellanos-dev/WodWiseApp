package com.migueldev.wodwiseapp.presentation.screen.theme

data class ThemeSwitcher(
    val themeMode: String,
    val value: Boolean,
) {
    companion object ThemeMode {
        const val DARK = "Dark"
        const val LIGHT = "Light"
    }
}
