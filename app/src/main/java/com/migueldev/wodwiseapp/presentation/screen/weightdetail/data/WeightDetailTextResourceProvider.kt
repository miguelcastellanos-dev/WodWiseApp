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
            ),
            dateInputTextFieldLabel = resourceProvider.getString(
                R.string.date_input_textField_label
            ),
            dateInputTextFieldPlaceholder = resourceProvider.getString(
                R.string.date_input_textField_placeholder
            ),
            weightHistoryDialogTitle = resourceProvider.getString(
                R.string.weight_history_dialog_title
            ),
            weightInputTextFieldLabel = resourceProvider.getString(
                R.string.weight_input_textField_label
            ),
            weightInputTextFieldPlaceholder = resourceProvider.getString(
                R.string.weight_input_textField_placeholder
            ),
            repetitionsInputTextFieldLabel = resourceProvider.getString(
                R.string.repetitions_input_textField_label
            ),
            repetitionsInputTextFieldPlaceholder = resourceProvider.getString(
                R.string.repetitions_input_textField_placeholder
            ),
            repsText = resourceProvider.getString(
                R.string.reps_text
            ),
            historyListTitle = resourceProvider.getString(
                R.string.history_list_title
            ),
            emptyHistoryList = resourceProvider.getString(
                R.string.empty_history_list
            ),
            abbreviationsForUnitsOfWeight = resourceProvider.getString(
                R.string.abbreviations_for_units_of_weight
            ),
            openingParenthesis = resourceProvider.getString(
                R.string.opening_parenthesis
            ),
            closingParenthesis = resourceProvider.getString(
                R.string.closing_parenthesis
            )
        )
    }
}
