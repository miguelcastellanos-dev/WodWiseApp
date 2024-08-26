package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.migueldev.wodwiseapp.presentation.screen.calendar.CalendarViewModel
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.CalendarState
import com.migueldev.wodwiseapp.presentation.screen.theme.Dimension

@Composable
fun CalendarWeekHeaderCard(
    week: String,
    calendarViewModel: CalendarViewModel,
    calendarState: CalendarState,
) {
    val showDayCardState = calendarState.showWeekCardState[week] == true
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                calendarViewModel.foldOrUnfoldWeekTab(week)
            },
        colors = CardDefaults.cardColors(
            containerColor =
            if (showDayCardState) {
                MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
            } else {
                MaterialTheme.colorScheme.background
            }
        ),
        shape = RectangleShape,
        border = BorderStroke(
            Dimension.d1,
            if (showDayCardState) {
                MaterialTheme.colorScheme.primary
            } else {
                MaterialTheme.colorScheme.secondaryContainer
            }
        )
    ) {
        Text(
            text = "${calendarState.prefixTitleWeekText} $week",
            modifier = Modifier
                .padding(Dimension.d16),
            style = TextStyle(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onBackground
        )
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
}
