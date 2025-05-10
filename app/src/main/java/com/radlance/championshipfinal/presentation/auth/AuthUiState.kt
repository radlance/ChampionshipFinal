package com.radlance.championshipfinal.presentation.auth

data class AuthUiState(
    val invalidFirstNameMessage: String = "",
    val invalidLastNameMessage: String = "",
    val invalidPatronymicMessage: String = "",
    val invalidDateOfBirthMessage: String = "",
    val invalidTelegramMessage: String = "",
    val invalidEmailMessage: String = "",
    val invalidPasswordMessage: String = "",
    val invalidConfirmPasswordMessage: String = ""
)
