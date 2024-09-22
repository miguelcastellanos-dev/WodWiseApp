package com.migueldev.wodwiseapp.presentation.screen.user.data

import com.migueldev.wodwiseapp.R
import com.migueldev.wodwiseapp.presentation.framework.ResourceProvider
import javax.inject.Inject

class UserTextResourceProvider @Inject constructor(
    private val resourceProvider: ResourceProvider,
) {
    fun userInitializeTextResources(): LoginState {
        return LoginState(
            loginButtonText = resourceProvider.getString(
                R.string.login_button_text
            ),
            signupButtonText = resourceProvider.getString(
                R.string.signUp_button_text
            ),
            forgotPasswordText = resourceProvider.getString(
                R.string.forgot_password_text
            ),
            signupQuestionText = resourceProvider.getString(
                R.string.signUp_question
            ),
            clickableSignupText = resourceProvider.getString(
                R.string.clickable_signUp_text
            ),
            descriptionLogoApp = resourceProvider.getString(
                R.string.description_logo_app
            ),
            hintEmail = resourceProvider.getString(
                R.string.hint_email
            ),
            hintPassword = resourceProvider.getString(
                R.string.hint_password
            ),
            hintRepeatPassword = resourceProvider.getString(
                R.string.hint_repeat_password
            ),
            descriptionVisibilityIcon = resourceProvider.getString(
                R.string.description_visibility_icon
            ),
            descriptionCloseAppIcon = resourceProvider.getString(
                R.string.description_close_app_icon
            ),
            resetPassword = resourceProvider.getString(
                R.string.reset_password
            ),
            incorrectPasswordOrEmail = resourceProvider.getString(
                R.string.incorrect_password_or_email
            ),
            incorrectRequest = resourceProvider.getString(
                R.string.incorrect_request
            ),
            confirmSendEmailButtonText = resourceProvider.getString(
                R.string.confirm_send_email_button_text
            ),
            cancelSendEmailButtonText = resourceProvider.getString(
                R.string.cancel_send_email_button_text
            ),
            forgotPasswordTitleText = resourceProvider.getString(
                R.string.forgot_password_title_text
            ),
            appName = resourceProvider.getString(
                R.string.app_name
            ),
            appSloganText = resourceProvider.getString(
                R.string.app_slogan_text
            ),
            emailInformationTitle = resourceProvider.getString(
                R.string.email_information_title
            ),
            emailInformationText = resourceProvider.getString(
                R.string.email_information_text
            ),
            okReplyText = resourceProvider.getString(
                R.string.ok_reply_text
            )
        )
    }
}
