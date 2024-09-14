package com.migueldev.wodwiseapp.presentation.screen.profile

import androidx.compose.runtime.Composable
import com.migueldev.wodwiseapp.presentation.screen.profile.composables.ProfileScaffold
import com.migueldev.wodwiseapp.presentation.screen.profile.data.ProfileState
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState

@Composable
fun ProfileScreen(
    settingState: SettingState,
    profileState: ProfileState,
    onBackClicked: () -> Unit,
    onLogoutClicked: () -> Unit,
    onDeleteUserClicked: () -> Unit,
    onDeleteAllWorkoutsClicked: () -> Unit,
    onDeleteAllWeightsClicked: () -> Unit,
) {
    ProfileScaffold(
        settingState = settingState,
        profileState = profileState,
        onBackClicked = onBackClicked,
        onLogoutClicked = onLogoutClicked,
        onDeleteUserClicked = onDeleteUserClicked,
        onDeleteAllWorkoutsClicked = onDeleteAllWorkoutsClicked,
        onDeleteAllWeightsClicked = onDeleteAllWeightsClicked
    )
}
