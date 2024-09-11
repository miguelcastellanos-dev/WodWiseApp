package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState

@Composable
fun ScaffoldDropdownMenu(
    navController: NavController,
    scaffoldState: ScaffoldState,
    appActionState: AppActionState,
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "Scaffold drop down menu",
            tint = MaterialTheme.colorScheme.onBackground
        )
    }
    DropdownMenu(
        modifier = Modifier.background(MaterialTheme.colorScheme.background),
        expanded = expanded,
        onDismissRequest = { expanded = false }
    ) {
        DropdownMenuItem(
            text = { Text(scaffoldState.profileItemText) },
            onClick = { },
            leadingIcon = { Icon(Icons.Outlined.Person, contentDescription = "Icon profile") }
        )
        DropdownMenuItem(
            text = { Text(scaffoldState.settingsItemText) },
            onClick = {
                expanded = false
                navController.navigate(Routes.SettingScreen.route)
            },
            leadingIcon = { Icon(Icons.Outlined.Settings, contentDescription = "Icon settings") }
        )
        HorizontalDivider()
        DropdownMenuItem(
            text = { Text(scaffoldState.signOffItemText) },
            onClick = { appActionState.onLogoutClicked() },
            leadingIcon = { Icon(Icons.Outlined.Close, contentDescription = "Icon sign off") }
        )
    }
}
