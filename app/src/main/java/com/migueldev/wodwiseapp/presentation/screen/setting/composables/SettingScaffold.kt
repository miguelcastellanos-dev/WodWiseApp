package com.migueldev.wodwiseapp.presentation.screen.setting.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@Composable
fun SettingScaffold(
    settingState: SettingState,
    onThemeSwitched: () -> Unit,
    onBackClicked: () -> Unit,
) {
    WodWiseAppTheme(
        darkTheme = settingState.mode.themeMode == ThemeSwitcher.DARK,
        content = {
            Scaffold(
                topBar = {
                    SettingTopBar(
                        settingState = settingState,
                        onBackClicked = onBackClicked
                    )
                },
                content = { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        ThemeSetting(
                            settingState = settingState,
                            onThemeSwitched = onThemeSwitched
                        )
                    }
                }
            )
        }
    )
}
