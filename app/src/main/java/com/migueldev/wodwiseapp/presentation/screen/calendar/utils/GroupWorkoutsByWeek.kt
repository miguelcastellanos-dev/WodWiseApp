package com.migueldev.wodwiseapp.presentation.screen.calendar.utils

import android.os.Build
import androidx.annotation.RequiresApi
import com.migueldev.wodwiseapp.domain.exception.InvalidDateException
import com.migueldev.wodwiseapp.presentation.screen.calendar.data.WorkoutCardData
import java.time.DayOfWeek
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
fun groupWorkoutsByWeek(workouts: List<WorkoutCardData>): Map<String, List<WorkoutCardData>> {
    return workouts.groupBy { workout ->
        val dateString = workout.date
        val localDate = convertStringToLocalDate(dateString, STRING_TO_LOCAL_DATE_PATTERN)

        localDate?.let {
            val weekStart = it.with(DayOfWeek.MONDAY)
            val formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_OUTPUT_PATTERN)
            weekStart.format(formatter)
        } ?: throw InvalidDateException()
    }
}

private const val STRING_TO_LOCAL_DATE_PATTERN = "EEEE dd MMMM yyyy"
private const val DATE_FORMAT_OUTPUT_PATTERN = "dd-MM-yyyy"
