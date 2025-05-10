package com.radlance.championshipfinal.presentation.auth

import com.radlance.championshipfinal.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    validation: ValidationAuth
) : BaseAuthViewModel(validation) {

    private val _signInResultUiState =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val signInResultUiState: StateFlow<FetchResultUiState<Unit>>
        get() = _signInResultUiState.asStateFlow()

    private val _signUpResultUiState =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val signUpResultUiState: StateFlow<FetchResultUiState<Unit>>
        get() = _signUpResultUiState.asStateFlow()

    private val _recoverPasswordResultUiState =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val recoverPasswordResultUiState: StateFlow<FetchResultUiState<Unit>>
        get() = _recoverPasswordResultUiState.asStateFlow()

    fun signIn(email: String, password: String) {
        validateFields(email = email, password = password)

        with(authUiState.value) {
            if (invalidEmailMessage.isBlank() && invalidPasswordMessage.isBlank()) {
                handle(action = { _signInResultUiState.value = FetchResultUiState.Loading() }) {
                    _signInResultUiState.value = FetchResultUiState.Success(Unit)
                }
            }
        }
    }

    fun createProfile(
        firstName: String,
        patronymic: String,
        lastName: String,
        dateOfBirth: String,
        gender: String,
        telegram: String
    ) {
        validateFields(
            firstName = firstName,
            patronymic = patronymic,
            lastName = lastName,
            dateOfBirth = dateOfBirth,
            gender = gender,
            telegram = telegram
        )

        with(authUiState.value) {
            if (invalidFirstNameMessage.isBlank()
                && invalidPatronymicMessage.isBlank()
                && invalidLastNameMessage.isBlank()
                && invalidDateOfBirthMessage.isBlank()
                && invalidTelegramMessage.isBlank()
            ) {
                handle(action = { _signUpResultUiState.value = FetchResultUiState.Loading() }) {
                    _signUpResultUiState.value = FetchResultUiState.Success(Unit)
                }
            }
        }
    }

    fun recoverPassword(password: String, confirmPassword: String) {
        validateFields(password = password, confirmPassword = confirmPassword)

        with(authUiState.value) {
            if (invalidPasswordMessage.isBlank() && invalidConfirmPasswordMessage.isBlank()) {
                handle(
                    action = {
                        _recoverPasswordResultUiState.value = FetchResultUiState.Loading()
                    }
                ) {
                    _recoverPasswordResultUiState.value = FetchResultUiState.Success(Unit)
                }
            }
        }
    }
}