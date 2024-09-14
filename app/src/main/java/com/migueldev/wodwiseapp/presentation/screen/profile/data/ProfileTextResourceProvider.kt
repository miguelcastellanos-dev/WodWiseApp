package com.migueldev.wodwiseapp.presentation.screen.profile.data

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class ProfileTextResourceProvider @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun profileInitializeTextResources(): ProfileState {
        return ProfileState(
            profileTitleText = resourceProvider.getString(
                R.string.profileTitleText
            ),
            closeSessionText = resourceProvider.getString(
                R.string.close_session_text
            ),
            deleteUserText = resourceProvider.getString(
                R.string.delete_user_text
            ),
            deleteWorkoutsDatabaseText = resourceProvider.getString(
                R.string.delete_workouts_database_text
            ),
            deleteWeightsDatabaseText = resourceProvider.getString(
                R.string.delete_weights_database_text
            ),
            closeSessionWarningText = resourceProvider.getString(
                R.string.close_session_warning_text
            ),
            deleteUserWarningText = resourceProvider.getString(
                R.string.delete_user_warning_text
            ),
            deleteWorkoutsWarningText = resourceProvider.getString(
                R.string.delete_workouts_warning_text
            ),
            deleteWeightsWarningText = resourceProvider.getString(
                R.string.delete_weights_warning_text
            ),
            deleteUserDialogWarningText = resourceProvider.getString(
                R.string.delete_user_dialog_warning_text
            ),
            deleteWorkoutsDialogWarningText = resourceProvider.getString(
                R.string.delete_workouts_dialog_warning_text
            ),
            deleteWeightsDialogWarningText = resourceProvider.getString(
                R.string.delete_weights_dialog_warning_text
            ),
            warningDialogTitle = resourceProvider.getString(
                R.string.warning_dialog_title
            ),
            warningDialogConfirmButton = resourceProvider.getString(
                R.string.warning_dialog_confirm_button
            ),
            warningDialogCancelButton = resourceProvider.getString(
                R.string.warning_dialog_cancel_button
            ),
            closeSessionDialogWarningText = resourceProvider.getString(
                R.string.close_session_dialog_warning_text
            ),
            deleteUserToastWrapperTest = resourceProvider.getString(
                R.string.delete_user_toast_wrapper_test
            ),
            deleteWorkoutsToastWrapperTest = resourceProvider.getString(
                R.string.delete_workouts_toast_wrapper_test
            ),
            deleteWeightsToastWrapperTest = resourceProvider.getString(
                R.string.delete_weights_toast_wrapper_test
            )
        )
    }
}
