package com.migueldev.wodwiseapp.presentation.screen.weight.data

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class WeightTextResourceProvider @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun weightInitializeTextResources(): WeightsState {
        return WeightsState(
            emptyWeightText = resourceProvider.getString(
                R.string.weight_empty_text
            ),
            confirmButtonDialogText = resourceProvider.getString(
                R.string.weight_confirm_button_dialog
            ),
            dismissButtonDialogText = resourceProvider.getString(
                R.string.weight_dismiss_button_dialog
            ),
            titleAddWeightDialogText = resourceProvider.getString(
                R.string.add_weight_dialog_title
            ),
            textFieldAddExerciseNameDialogText = resourceProvider.getString(
                R.string.add_exercise_name_dialog_text_field
            ),
            textFieldAddRmWeightDialogText = resourceProvider.getString(
                R.string.add_rm_weight_dialog_text_field
            ),
            contentDescriptionFab = resourceProvider.getString(
                R.string.content_description_fab
            ),
            weightInformationTitleText = resourceProvider.getString(
                R.string.weight_information_title_text
            ),
            weightInformationText = resourceProvider.getString(
                R.string.weight_information_text
            )
        )
    }
}
