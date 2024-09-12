package com.migueldev.wodwiseapp.presentation.screen.profile.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@Composable
fun ProfileScaffold(
    settingState: SettingState,
    profileState: ProfileState,
    onBackClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onDeleteUserClicked: () -> Unit,
    onDeleteAllWorkoutsClicked: () -> Unit,
    onDeleteAllWeightsClicked: () -> Unit,
) {
    WodWiseAppTheme(
        darkTheme = settingState.mode.themeMode == ThemeSwitcher.DARK,
        content = {
            Scaffold(
                topBar = {
                    ProfileTopBar(
                        settingState = settingState,
                        profileState = profileState,
                        onBackClicked = onBackClicked
                    )
                },
                content = { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        CloseSessionClickableText(
                            profileState = profileState,
                            onLogoutClicked = onLogoutClicked
                        )
                        DeleteUserClickableText(
                            profileState = profileState,
                            onDeleteUserClicked = onDeleteUserClicked
                        )
                        DeleteAllWorkoutsClickableText(
                            profileState = profileState,
                            onDeleteAllWorkoutsClicked = onDeleteAllWorkoutsClicked
                        )
                        DeleteAllWeightsClickableText(
                            profileState = profileState,
                            onDeleteAllWeightsClicked = onDeleteAllWeightsClicked
                        )
                    }
                }
            )
        }
    )
}
