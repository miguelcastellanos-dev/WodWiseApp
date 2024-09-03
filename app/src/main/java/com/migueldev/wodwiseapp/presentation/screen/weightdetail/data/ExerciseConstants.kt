package com.migueldev.wodwiseapp.presentation.screen.weightdetail.data

@Suppress("MagicNumber")
object ExerciseConstants {
    val HighPercentages = listOf(105.0, 102.5, 100.0, 97.5, 95.0, 92.5, 90.0, 87.5, 85.0, 82.5)
    val LowPercentages = listOf(80.0, 75.0, 70.0, 65.0, 60.0, 55.0, 50.0, 45.0, 40.0, 35.0)
    val LowerReps = listOf(2, 3, 4, 5, 6, 7, 8)
    val HigherReps = listOf(9, 10, 11, 12, 13, 14, 15)
}

object WeightCalculationConstants {
    const val ROUNDING_FACTOR = 4
    const val PERCENTAGE_DIVISOR = 100.0
    const val WEIGHT_REPS_MULTIPLIER = 0.033
}
