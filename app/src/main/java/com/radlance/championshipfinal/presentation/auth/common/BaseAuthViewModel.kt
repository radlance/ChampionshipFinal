package com.radlance.championshipfinal.presentation.auth.common

import com.radlance.championshipfinal.presentation.common.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

abstract class BaseAuthViewModel(private val validation: ValidationAuth) : BaseViewModel() {

    private val _authUiState = MutableStateFlow(AuthUiState())

    val authUiState: StateFlow<AuthUiState>
        get() = _authUiState.asStateFlow()

    fun validateFields(
        firstName: String? = null,
        lastName: String? = null,
        patronymic: String? = null,
        dateOfBirth: String? = null,
        gender: String? = null,
        telegram: String? = null,
        email: String? = null,
        password: String? = null,
        confirmPassword: String? = null
    ) {
        _authUiState.update { currentState ->
            currentState.copy(
                invalidFirstNameMessage = firstName?.let { validation.validFirstName(it) } ?: "",
                invalidLastNameMessage = lastName?.let { validation.validLastName(it) } ?: "",
                invalidPatronymicMessage = patronymic?.let { validation.validLastName(it) } ?: "",
                invalidDateOfBirthMessage = dateOfBirth?.let { validation.validDateOfBirth(it) }
                    ?: "",
                invalidTelegramMessage = telegram?.let { validation.validTelegram(it) } ?: "",
                invalidEmailMessage = email?.let { validation.validEmail(it) } ?: "",
                invalidPasswordMessage = password?.let { validation.validPassword(it) } ?: "",
                invalidConfirmPasswordMessage = confirmPassword?.let { validation.validConfirmPassword(password ?: "", it) } ?: ""
            )
        }
    }

    fun resetFirstNameError() = _authUiState.update { it.copy(invalidFirstNameMessage = "") }

    fun resetLastFirstNameError() = _authUiState.update { it.copy(invalidLastNameMessage = "") }

    fun resetPatronymicError() = _authUiState.update { it.copy(invalidPatronymicMessage = "") }

    fun resetDateOfBirthError() = _authUiState.update { it.copy(invalidDateOfBirthMessage = "") }

    fun resetTelegramError() = _authUiState.update { it.copy(invalidTelegramMessage = "") }

    fun resetEmailError() = _authUiState.update { it.copy(invalidEmailMessage = "") }

    fun resetPasswordError() = _authUiState.update { it.copy(invalidPasswordMessage = "") }

    fun resetConfirmPasswordError() = _authUiState.update { it.copy(invalidConfirmPasswordMessage = "") }
}