package com.radlance.championshipfinal.presentation.auth.common

import com.radlance.championshipfinal.domain.auth.AuthRepository
import com.radlance.championshipfinal.domain.profile.User
import com.radlance.championshipfinal.presentation.common.FetchResultMapper
import com.radlance.championshipfinal.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    validation: ValidationAuth
) : BaseAuthViewModel(validation) {

    private val _signInResultUiState =
        MutableStateFlow<FetchResultUiState<Boolean>>(FetchResultUiState.Initial())
    val signInResultUiState: StateFlow<FetchResultUiState<Boolean>>
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
                _signInResultUiState.value = FetchResultUiState.Loading()
                handle(
                    action = {
                        authRepository.signIn(User(email = email, password = password))
                    }
                ) {
                    _signInResultUiState.value = it.map(FetchResultMapper())
                }
            }
        }
    }

    fun createProfile(
        email: String,
        password: String,
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
                _signUpResultUiState.value = FetchResultUiState.Loading()
                handle(
                    action = {
                        authRepository.signUp(
                            User(
                                email = email,
                                password = password,
                                firstName = firstName,
                                patronymic = patronymic,
                                lastName = lastName,
                                dateOfBirth = dateOfBirth,
                                gender = gender,
                                telegram = telegram
                            )
                        )
                    }
                ) {
                    _signUpResultUiState.value = it.map(FetchResultMapper())
                }
            }
        }
    }

    fun recoverPassword(password: String, confirmPassword: String) {
        validateFields(password = password, confirmPassword = confirmPassword)

        with(authUiState.value) {
            if (invalidPasswordMessage.isBlank() && invalidConfirmPasswordMessage.isBlank()) {
                _recoverPasswordResultUiState.value = FetchResultUiState.Loading()
                handle(action = {}) {
                    _recoverPasswordResultUiState.value = FetchResultUiState.Success(Unit)
                }
            }
        }
    }

    fun resetStates() {
        _signInResultUiState.value = FetchResultUiState.Initial()
        _signUpResultUiState.value = FetchResultUiState.Initial()
    }
}