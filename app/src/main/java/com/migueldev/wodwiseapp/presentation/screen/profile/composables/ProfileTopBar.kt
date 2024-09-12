package com.migueldev.wodwiseapp.presentation.screen.profile.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileTopBar(
    settingState: SettingState,
    profileState: ProfileState,
    onBackClicked: () -> Unit,
) {
    WodWiseAppTheme(
        darkTheme = settingState.mode.themeMode == ThemeSwitcher.DARK,
        content = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = profileState.profileTitleText,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground,
                            modifier = Modifier.weight(WEIGHT_TITLE_TEXT)
                        )
                        Spacer(modifier = Modifier.weight(WEIGHT_SPACER))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    )
}

private const val WEIGHT_TITLE_TEXT = 6f
private const val WEIGHT_SPACER = 1f
