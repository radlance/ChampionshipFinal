package com.radlance.championshipfinal.presentation.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.radlance.championshipfinal.R
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ResetPasswordScreen(
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordFieldValue by rememberSaveable { mutableStateOf("") }
    var confirmPasswordFieldValue by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

    BackHandler(onBack = onBackPressed)
    Column(
        modifier = modifier
            .safeGesturesPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = CustomTheme.elevation.spacing20dp)
    ) {
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
            onValueChange = { passwordFieldValue = it },
            errorMessage = "",
            label = stringResource(R.string.new_password),
            hint = "",
            isPassword = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(14.dp))
        EnterInputField(
            value = confirmPasswordFieldValue,
            onValueChange = { confirmPasswordFieldValue = it },
            errorMessage = "",
            label = stringResource(R.string.reply_password),
            hint = "",
            isPassword = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(Modifier.height(10.dp))
        AppButton(
            onClick = {
                keyboardController?.hide()
            },
            label = stringResource(R.string.next),
            buttonState = ButtonState.Big,
            enabled = confirmPasswordFieldValue.isNotEmpty() && passwordFieldValue.isNotEmpty()
        )
    }
}