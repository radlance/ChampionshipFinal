package com.radlance.championshipfinal.presentation.auth.recover

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.auth.common.AuthViewModel
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ResetPasswordScreen(
    onBackPressed: () -> Unit,
    navigateToSignInScreen: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AuthViewModel = hiltViewModel()
) {
    val authUiState by viewModel.authUiState.collectAsState()
    val resetPasswordResultUiState by viewModel.recoverPasswordResultUiState.collectAsState()

    var passwordFieldValue by rememberSaveable { mutableStateOf("") }
    var confirmPasswordFieldValue by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    resetPasswordResultUiState.Show(
        onSuccess = { navigateToSignInScreen() },
        onError = {},
        onLoading = { keyboardController?.hide() }
    )

    BackHandler(onBack = onBackPressed)
    BaseColumn(modifier = modifier) {
        Spacer(Modifier.height(59.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.emoji_hello),
                contentDescription = "emoji_hello",
                modifier = Modifier.size(32.dp)
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = stringResource(R.string.password_creation),
                style = CustomTheme.typography.title1Heavy
            )
        }
        Spacer(Modifier.height(23.dp))
        Text(
            text = stringResource(R.string.enter_new_password),
            style = CustomTheme.typography.textRegular
        )
        Spacer(Modifier.height(CustomTheme.elevation.spacing64dp))
        EnterInputField(
            value = passwordFieldValue,
            onValueChange = {
                passwordFieldValue = it
                viewModel.resetPasswordError()
            },
            errorMessage = authUiState.invalidPasswordMessage,
            label = stringResource(R.string.new_password),
            hint = "",
            isPassword = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(14.dp))
        EnterInputField(
            value = confirmPasswordFieldValue,
            onValueChange = {
                confirmPasswordFieldValue = it
                viewModel.resetConfirmPasswordError()
            },
            errorMessage = authUiState.invalidConfirmPasswordMessage,
            label = stringResource(R.string.reply_password),
            hint = "",
            isPassword = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(10.dp))
        AppButton(
            onClick = {
                viewModel.recoverPassword(passwordFieldValue, confirmPasswordFieldValue)
            },
            label = stringResource(R.string.next),
            buttonState = ButtonState.Big,
            enabled = confirmPasswordFieldValue.isNotEmpty() && passwordFieldValue.isNotEmpty()
        )
        Spacer(Modifier.height(10.dp))
    }
}