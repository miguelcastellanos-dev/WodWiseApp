package com.migueldev.wodwiseapp.presentation.screen.scaffold.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.model.Routes
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.BottomNavigationItemData
import com.migueldev.wodwiseapp.presentation.screen.scaffold.data.ScaffoldState

@Composable
fun ScaffoldBottomNavigationBar(
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
            route = Routes.WorkoutScreen,
            iconPainter = painterResource(id = R.drawable.save_screen_icon),
            text = scaffoldState.addWorkoutIconText
        ),
        BottomNavigationItemData(
            route = Routes.WeightsScreen,
            iconPainter = painterResource(id = R.drawable.weights_screen_icon),
            text = scaffoldState.weightsIconText
        ),
        BottomNavigationItemData(
            route = Routes.CoachScreen,
            iconPainter = painterResource(id = R.drawable.intelligence_screen_icon),
            text = scaffoldState.aIIconText
        )
    )
    ScaffoldNavigationBar(navController, items)
}
