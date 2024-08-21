package com.migueldev.wodwiseapp.presentation.screen.user.data

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoginEnabled: Boolean = false,
    val loginButtonText: String = "",
    val signupButtonText: String = "",
    val descriptionGoogleImage: String = "",
    val loginGoogleText: String = "",
    val forgotPasswordText: String = "",
    val signupQuestionText: String = "",
    val clickableSignupText: String = "",
    val loginDividerText: String = "",
    val descriptionLogoApp: String = "",
    val hintEmail: String = "",
    val hintPassword: String = "",
    val descriptionVisibilityIcon: String = "",
    val descriptionCloseAppIcon: String = "",
)
