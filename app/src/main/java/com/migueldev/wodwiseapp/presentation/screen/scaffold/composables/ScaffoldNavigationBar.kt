package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.BottomNavigationItemData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun ScaffoldNavigationBar(
    navController: NavHostController,
    items: List<BottomNavigationItemData>,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.background
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = backStackEntry?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = item.iconPainter,
                        contentDescription = null,
                        modifier = Modifier.size(Dimension.d24)
                    )
                },
                label = {
                    Text(
                        text = item.text
                    )
                },
                selected = currentRoute == item.route.route,
                onClick = { },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f),
                    unselectedIconColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    selectedTextColor = MaterialTheme.colorScheme.onBackground,
                    unselectedTextColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.3f),
                    indicatorColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                )
            )
        }
    }
}
