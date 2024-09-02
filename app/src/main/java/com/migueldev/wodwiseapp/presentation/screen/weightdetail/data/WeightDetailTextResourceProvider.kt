package com.migueldev.wodwiseapp.presentation.screen.weightdetail.data

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class WeightDetailTextResourceProvider @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun initializeTextResources(): WeightDetailState {
        return WeightDetailState(
            exerciseRmTitleText = resourceProvider.getString(
                R.string.exercise_rm_title
            ),
            titlePercentagesViewDetailText = resourceProvider.getString(
                R.string.title_percentages_view_detail
            ),
            percentageSignText = resourceProvider.getString(
                R.string.percentage_sign_text
            ),
            titleRepsDetailViewText = resourceProvider.getString(
                R.string.title_reps_detail_view
            ),
            titleUpdateRmDialogText = resourceProvider.getString(
                R.string.title_update_rm_dialog
            ),
            newRmDialogText = resourceProvider.getString(
                R.string.text_new_rm_dialog
            ),
            confirmButtonDialogText = resourceProvider.getString(
                R.string.confirm_button_dialog
            ),
            dismissButtonDialogText = resourceProvider.getString(
                R.string.dismiss_button_dialog
            ),
            emptyWeightText = resourceProvider.getString(
                R.string.text_empty_weight
            )
        )
    }
}
