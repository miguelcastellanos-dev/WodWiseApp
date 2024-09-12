package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import com.migueldev.wodwiseapp.presentation.screen.setting.data.SettingState
import com.migueldev.wodwiseapp.presentation.screen.theme.ThemeSwitcher
import com.migueldev.wodwiseapp.presentation.screen.theme.WodWiseAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldTopBar(
    navController: NavController,
    scaffoldState: ScaffoldState,
    settingState: SettingState,
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
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(WEIGHT_TITLE_TEXT),
                            text = scaffoldState.scaffoldTitleText,
                            textAlign = TextAlign.Center,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Spacer(modifier = Modifier.weight(WEIGHT_SPACER))
                    }
                },
                navigationIcon = {
                    ScaffoldDropdownMenu(
                        navController = navController,
                        scaffoldState = scaffoldState
                    )
                }
            )
        }
    )
}

private const val WEIGHT_TITLE_TEXT = 6f
private const val WEIGHT_SPACER = 1f
