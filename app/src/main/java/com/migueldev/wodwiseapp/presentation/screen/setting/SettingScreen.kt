package com.migueldev.wodwiseapp.presentation.screen.setting

import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.setting.composables.SettingScaffold
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState

@Composable
fun SettingScreen(
    settingState: SettingState,
    onThemeSwitched: () -> Unit,
    onBackClicked: () -> Unit,
) {
    SettingScaffold(
        settingState = settingState,
        onThemeSwitched = onThemeSwitched,
        onBackClicked = onBackClicked
    )
}
