package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.migueldev.wodwiseapp.presentation.navigation.AppActionState
import com.migueldev.wodwiseapp.presentation.navigation.AppState
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarCardViewContent(
    appState: AppState,
    appActionState: AppActionState,
    workoutCardData: WorkoutCardData,
    isCardClickable: Boolean,
) {
    Card(
        border = BorderStroke(Dimension.d1, MaterialTheme.colorScheme.secondaryContainer),
        modifier = Modifier
            .padding(
                start = Dimension.d8,
                end = Dimension.d8,
                bottom = Dimension.d8
            )
            .fillMaxWidth()
            .run {
                if (isCardClickable) {
                    clickable { }
                } else {
                    this
                }
            },
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        ),
        shape = RoundedCornerShape(Dimension.d8)
    ) {
        CalendarCardView(
            appState = appState,
            appActionState = appActionState,
            workoutCardData = workoutCardData
        )
    }
}
