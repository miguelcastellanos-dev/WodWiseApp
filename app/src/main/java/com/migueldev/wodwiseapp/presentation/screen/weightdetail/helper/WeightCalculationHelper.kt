package com.migueldev.wodwiseapp.presentation.screen.weightdetail.helper

import com.migueldev.wodwiseapp.presentation.screen.weightdetail.data.WeightCalculationConstants
import java.util.Locale

fun roundToNearestQuarter(value: Double): Double {
    return (
        Math.round(value * WeightCalculationConstants.ROUNDING_FACTOR) /
            WeightCalculationConstants.ROUNDING_FACTOR.toDouble()
        )
}

fun calculatePercentages(percentage: Double, kg: Double): String {
    val result = (percentage / WeightCalculationConstants.PERCENTAGE_DIVISOR) * kg
    val roundedResult = roundToNearestQuarter(result)
    return String.format(Locale.US, ROUNDED_RESULT_FORMAT, roundedResult)
}

fun calculateWeightForReps(reps: Int, rm: Double): String {
    val result = rm / (1 + WeightCalculationConstants.WEIGHT_REPS_MULTIPLIER * reps)
    val roundedResult = roundToNearestQuarter(result)
    return String.format(Locale.US, ROUNDED_RESULT_FORMAT, roundedResult)
}

private const val ROUNDED_RESULT_FORMAT = "%.2f kg "
