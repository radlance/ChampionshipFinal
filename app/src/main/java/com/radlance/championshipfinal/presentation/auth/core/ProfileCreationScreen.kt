package com.radlance.championshipfinal.presentation.auth.core

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.auth.common.AuthViewModel
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.component.select.AppSelector
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ProfileCreationScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val authUiState by viewModel.authUiState.collectAsState()
    val signUpResultUiState by viewModel.signUpResultUiState.collectAsState()

    var nameFieldValue by rememberSaveable { mutableStateOf("") }
    var patronymicFieldValue by rememberSaveable { mutableStateOf("") }
    var lastNameFieldValue by rememberSaveable { mutableStateOf("") }
    var dateOfBirthFieldValue by rememberSaveable { mutableStateOf("") }
    var telegramFieldValue by rememberSaveable { mutableStateOf("") }
    var selectedGender by rememberSaveable { mutableStateOf<String?>(null) }

    signUpResultUiState.Show(
        onSuccess = { navigateToHome() },
        onError = {},
        onLoading = { keyboardController?.hide() }
    )

    Column(
        modifier = modifier
            .safeGesturesPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = CustomTheme.elevation.spacing20dp)
    ) {
        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))
        Text(
            text = stringResource(R.string.profile_creation),
            style = CustomTheme.typography.title1Heavy
        )
        Spacer(Modifier.height(44.dp))
        Text(
            text = stringResource(R.string.without_profile),
            style = CustomTheme.typography.captionRegular.copy(
                color = CustomTheme.colors.placeholder
            )
        )
        Spacer(Modifier.height(CustomTheme.elevation.spacing8dp))
        Text(
            text = stringResource(R.string.profile_will_be_store),
            style = CustomTheme.typography.captionRegular.copy(
                color = CustomTheme.colors.placeholder
            )
        )
        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))
        Column(verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing24dp)) {
            EnterInputField(
                value = nameFieldValue,
                onValueChange = {
                    nameFieldValue = it
                    viewModel.resetFirstNameError()
                },
                errorMessage = authUiState.invalidFirstNameMessage,
                label = "",
                hint = stringResource(R.string.name_hint),
                selectedBorderColor = CustomTheme.colors.inputIcon
            )

            EnterInputField(
                value = patronymicFieldValue,
                onValueChange = {
                    patronymicFieldValue = it
                    viewModel.resetPatronymicError()
                },
                errorMessage = authUiState.invalidPatronymicMessage,
                label = "",
                hint = stringResource(R.string.patronymic_hint),
                selectedBorderColor = CustomTheme.colors.inputIcon
            )

            EnterInputField(
                value = lastNameFieldValue,
                onValueChange = {
                    lastNameFieldValue = it
                    viewModel.resetLastFirstNameError()
                },
                errorMessage = authUiState.invalidLastNameMessage,
                label = "",
                hint = stringResource(R.string.last_name_hint),
                selectedBorderColor = CustomTheme.colors.inputIcon
            )

            EnterInputField(
                value = dateOfBirthFieldValue,
                onValueChange = {
                    dateOfBirthFieldValue = it
                    viewModel.resetDateOfBirthError()
                },
                errorMessage = authUiState.invalidDateOfBirthMessage,
                label = "",
                hint = stringResource(R.string.date_of_birth_hint),
                selectedBorderColor = CustomTheme.colors.inputIcon
            )

            AppSelector(
                options = listOf(
                    R.string.male,
                    R.string.female
                ).map { stringResource(it) },
                selectedItem = selectedGender,
                onItemSelect = { selectedGender = it },
                hint = stringResource(R.string.gender),
                label = ""
            )

            EnterInputField(
                value = telegramFieldValue,
                onValueChange = {
                    telegramFieldValue = it
                    viewModel.resetTelegramError()
                },
                errorMessage = authUiState.invalidTelegramMessage,
                label = "",
                hint = stringResource(R.string.telegram_hint)
            )
        }
        Spacer(Modifier.height(CustomTheme.elevation.spacing24dp))
        Spacer(Modifier.weight(1f))
        AppButton(
            onClick = {
                selectedGender?.let {
                    viewModel.createProfile(
                        firstName = nameFieldValue,
                        patronymic = patronymicFieldValue,
                        lastName = lastNameFieldValue,
                        dateOfBirth = dateOfBirthFieldValue,
                        gender = it,
                        telegram = telegramFieldValue
                    )
                }
            },
            label = stringResource(R.string.create),
            enabled = nameFieldValue.isNotEmpty()
                    && patronymicFieldValue.isNotEmpty()
                    && lastNameFieldValue.isNotEmpty()
                    && dateOfBirthFieldValue.isNotEmpty()
                    && selectedGender != null
                    && telegramFieldValue.isNotEmpty(),
            buttonState = ButtonState.Big
        )
        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))
    }
}