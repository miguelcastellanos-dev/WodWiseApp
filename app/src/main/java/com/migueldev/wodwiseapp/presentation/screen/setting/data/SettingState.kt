package com.migueldev.wodwiseapp.presentation.screen.setting.data

import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher

data class SettingState(
    val mode: ThemeSwitcher = ThemeSwitcher(ThemeSwitcher.LIGHT, false),
    val settingTitleText: String = "",
    val themeSettingText: String = "",
)
