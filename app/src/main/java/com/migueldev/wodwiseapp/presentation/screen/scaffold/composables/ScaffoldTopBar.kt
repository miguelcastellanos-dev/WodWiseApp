package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Brightness6
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@Composable
fun ScaffoldTopBar(
    scaffoldState: ScaffoldState,
    appActionState: AppActionState,
) {
    ScaffoldTopAppBar(
        scaffoldState = scaffoldState,
        mode = scaffoldState.mode,
        appActionState = appActionState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopAppBar(
    scaffoldState: ScaffoldState,
    mode: ThemeSwitcher,
    appActionState: AppActionState,
) {
    WodWiseAppTheme(
        darkTheme = mode.themeMode == ThemeSwitcher.DARK,
        content = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("TOP_APP_BAR_TEXT_TITLE"),
                        text = scaffoldState.scaffoldTitleText,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                navigationIcon = {
                    ScaffoldDropdownMenu(
                        scaffoldState = scaffoldState,
                        appActionState = appActionState
                    )
                },
                actions = {
                    IconButton(onClick = { appActionState.onToggleThemeClicked() }) {
                        Icon(
                            imageVector = Icons.Filled.Brightness6,
                            contentDescription = "switch theme light or dark",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            )
        }
    )
}
