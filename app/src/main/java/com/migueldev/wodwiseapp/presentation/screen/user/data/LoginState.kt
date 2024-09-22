package com.migueldev.wodwiseapp.presentation.screen.user.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class LoginState(
    val showForgotPasswordDialog: MutableState<Boolean> = mutableStateOf(false),
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val loginButtonText: String = "",
    val signupButtonText: String = "",
    val forgotPasswordText: String = "",
    val signupQuestionText: String = "",
    val clickableSignupText: String = "",
    val descriptionLogoApp: String = "",
    val hintEmail: String = "",
    val hintPassword: String = "",
    val hintRepeatPassword: String = "",
    val descriptionVisibilityIcon: String = "",
    val descriptionCloseAppIcon: String = "",
    val resetPassword: String = "",
    val incorrectPasswordOrEmail: String = "",
    val incorrectRequest: String = "",
    val confirmSendEmailButtonText: String = "",
    val cancelSendEmailButtonText: String = "",
    val forgotPasswordTitleText: String = "",
    val appName: String = "",
    val appSloganText: String = "",
    val emailInformationTitle: String = "",
    val emailInformationText: String = "",
    val okReplyText: String = "",
)
