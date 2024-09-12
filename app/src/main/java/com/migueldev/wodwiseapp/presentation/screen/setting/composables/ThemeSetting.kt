package com.migueldev.wodwiseapp.presentation.screen.setting.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.ColorLens
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun ThemeSetting(
    settingState: SettingState,
    onThemeSwitched: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.d4)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(Dimension.d64)
                    .padding(Dimension.d4)
            ) {
                Icon(
                    modifier = Modifier
                        .size(Dimension.d24)
                        .align(Alignment.Center),
                    imageVector = Icons.Sharp.ColorLens,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
            Text(
                text = settingState.themeSettingText,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
            ThemeSwitcherBox(
                settingState = settingState,
                onThemeSwitched = onThemeSwitched
            )
        }
    }
}
