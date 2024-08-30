package com.migueldev.wodwiseapp.domain.usecase

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class CreateTrainingInfoUseCase @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    operator fun invoke(
        selectedTime: Int,
        selectedFormat: String,
        selectedExerciseLists: Set<String>,
    ): String {
        val exercisesList = selectedExerciseLists.joinToString(", ") { it }
        return resourceProvider.getString(
            R.string.training_info_user_coach,
            selectedTime,
            selectedFormat,
            exercisesList
        )
    }
}
