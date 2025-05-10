package com.radlance.championshipfinal.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.radlance.championshipfinal.R
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.button.LoginButton
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.theme.CustomTheme

@Composable
fun SignInScreen(
    navigateToOtpEnter: () -> Unit,
    navigateToPasswordCreation: () -> Unit,
    modifier: Modifier = Modifier
) {
    var emailFieldValue by rememberSaveable { mutableStateOf("") }
    var passwordFieldValue by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current

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
                text = stringResource(R.string.welcome),
                style = CustomTheme.typography.title1Heavy
            )
        }
        Spacer(Modifier.height(23.dp))
        Text(
            text = stringResource(R.string.sign_in_for_features),
            style = CustomTheme.typography.textRegular
        )
        Spacer(Modifier.height(CustomTheme.elevation.spacing64dp))
        EnterInputField(
            value = emailFieldValue,
            onValueChange = { emailFieldValue = it },
            errorMessage = "",
            label = stringResource(R.string.email_sign_in),
            hint = stringResource(R.string.email_hint),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(Modifier.height(14.dp))
        EnterInputField(
            value = passwordFieldValue,
            onValueChange = { passwordFieldValue = it },
            errorMessage = "",
            label = stringResource(R.string.password),
            hint = "",
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isPassword = true
        )
        Spacer(Modifier.height(14.dp))
        AppButton(
            onClick = {
                keyboardController?.hide()
                navigateToPasswordCreation()
            },
            label = stringResource(R.string.next),
            buttonState = ButtonState.Big,
            enabled = emailFieldValue.isNotEmpty() && passwordFieldValue.isNotEmpty()
        )
        Spacer(Modifier.height(15.dp))
        Text(
            text = stringResource(R.string.forgot_password),
            style = CustomTheme.typography.textRegular.copy(
                color = CustomTheme.colors.accent,
                textAlign = TextAlign.Center
            ),
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = ripple()
                ) {
                    navigateToOtpEnter()
                }
        )
        Spacer(Modifier.weight(1f))
        Column(verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp)) {
            Text(
                text = stringResource(R.string.or_sign_in_with),
                style = CustomTheme.typography.textRegular.copy(
                    color = CustomTheme.colors.placeholder,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.fillMaxWidth()
            )

            LoginButton(
                onClick = {
                    keyboardController?.hide()
                },
                label = stringResource(R.string.sign_in_with_vk),
                icon = com.radlance.uikit.R.drawable.ic_vk_login
            )

            LoginButton(
                onClick = {
                    keyboardController?.hide()
                },
                label = stringResource(R.string.sign_in_with_yandex),
                icon = com.radlance.uikit.R.drawable.ic_yandex_login
            )
        }
        Spacer(Modifier.height(56.dp))
    }
}