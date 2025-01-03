package com.migueldev.wodwiseapp.presentation.screen.calendar.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
                MaterialTheme.colorScheme.primary
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
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${calendarState.prefixTitleWeekText} $week",
                modifier = Modifier
                    .weight(1f)
                    .padding(Dimension.d16),
                style = TextStyle(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.onBackground
            )
            Icon(
                imageVector =
                if (showDayCardState) {
                    Icons.Default.ArrowDropUp
                } else {
                    Icons.Default.ArrowDropDown
                },
                contentDescription = "Expand icon",
                modifier = Modifier
                    .size(Dimension.d48)
                    .padding(end = Dimension.d16),
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
    Spacer(modifier = Modifier.size(Dimension.d8))
}
