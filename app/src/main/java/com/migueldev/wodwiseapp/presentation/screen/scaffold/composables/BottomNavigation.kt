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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.BottomNavigationItemData
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun BottomNavigation(
    scaffoldState: ScaffoldState,
    navController: NavHostController,
) {
    val items = listOf(
        BottomNavigationItemData(
            route = Routes.CalendarScreen,
            iconPainter = painterResource(id = R.drawable.calendar_screen_icon),
            text = scaffoldState.calendarIconText
        ),
        BottomNavigationItemData(
            route = Routes.SaveScreen,
            iconPainter = painterResource(id = R.drawable.save_screen_icon),
            text = scaffoldState.addWorkoutIconText
        ),
        BottomNavigationItemData(
            route = Routes.WeightsScreen,
            iconPainter = painterResource(id = R.drawable.weights_screen_icon),
            text = scaffoldState.weightsIconText
        ),
        BottomNavigationItemData(
            route = Routes.IntelligenceScreen,
            iconPainter = painterResource(id = R.drawable.intelligence_screen_icon),
            text = scaffoldState.aIIconText
        )
    )
    NavigationBar(navController, items)
}

@Composable
fun NavigationBar(
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
