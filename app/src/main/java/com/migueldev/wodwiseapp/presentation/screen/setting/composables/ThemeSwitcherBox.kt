package com.migueldev.wodwiseapp.presentation.screen.setting.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Brightness2
import androidx.compose.material.icons.sharp.BrightnessHigh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher

@Composable
fun ThemeSwitcherBox(
    settingState: SettingState,
    onThemeSwitched: () -> Unit,
) {
    var checked by remember {
        mutableStateOf(settingState.mode.themeMode == ThemeSwitcher.LIGHT)
    }

    Box(
        modifier = Modifier
            .size(Dimension.d64)
            .padding(Dimension.d4)
    ) {
        Switch(
            modifier = Modifier
                .padding(Dimension.d8)
                .size(Dimension.d24)
                .align(Alignment.Center),
            checked = checked,
            onCheckedChange = {
                onThemeSwitched()
                checked = !checked
            },
            thumbContent = if (checked) {
                {
                    Icon(
                        imageVector = Icons.Sharp.BrightnessHigh,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            } else {
                {
                    Icon(
                        imageVector = Icons.Sharp.Brightness2,
                        contentDescription = null,
                        modifier = Modifier.size(SwitchDefaults.IconSize)
                    )
                }
            },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colorScheme.primary,
                checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
                uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer
            )
        )
    }
}
