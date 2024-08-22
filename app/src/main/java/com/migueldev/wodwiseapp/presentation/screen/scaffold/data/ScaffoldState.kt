package com.migueldev.wodwiseapp.presentation.screen.scaffold.data

import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher

data class ScaffoldState(
    val mode: ThemeSwitcher = ThemeSwitcher(ThemeSwitcher.LIGHT, false),
    val profileItemText: String = "",
    val settingsItemText: String = "",
    val signOffItemText: String = "",
    val scaffoldTitleText: String = "",
    val calendarIconText: String = "",
    val addWorkoutIconText: String = "",
    val weightsIconText: String = "",
    val aIIconText: String = "",
)
