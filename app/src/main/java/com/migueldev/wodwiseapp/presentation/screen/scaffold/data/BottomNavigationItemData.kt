package com.migueldev.wodwiseapp.presentation.screen.scaffold.data

import androidx.compose.ui.graphics.painter.Painter
import com.migueldev.wodwiseapp.model.Routes

data class BottomNavigationItemData(
    val route: Routes,
    val iconPainter: Painter,
    val text: String,
)
