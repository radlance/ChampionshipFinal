package com.radlance.championshipfinal.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radlance.championshipfinal.R
import com.radlance.uikit.component.button.BubbleButton
import com.radlance.uikit.component.button.BubbleButtonState
import com.radlance.uikit.component.input.SingleInputField
import com.radlance.uikit.theme.CustomTheme
import kotlinx.coroutines.delay

@Composable
fun OtpEnterScreen(
    onBackPressed: () -> Unit,
    navigateToResetPassword: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: OtpEnterViewModel = viewModel()
) {
    val eventFlow = viewModel.navigateEvent
    val otpList = viewModel.otpList()

    var countdown by rememberSaveable { mutableIntStateOf(60) }
    var isTimerRunning by rememberSaveable { mutableStateOf(true) }

    val keyboardController = LocalSoftwareKeyboardController.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    LaunchedEffect(Unit) {
        eventFlow.flowWithLifecycle(lifecycle, Lifecycle.State.STARTED).collect {
            keyboardController?.hide()
            navigateToResetPassword()
        }
    }

    LaunchedEffect(isTimerRunning) {
        if (isTimerRunning) {
            while (countdown > 0) {
                delay(1000L)
                countdown--
            }
            isTimerRunning = false
            countdown = 60
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .safeDrawingPadding(),
        contentAlignment = Alignment.Center
    ) {
        BubbleButton(
            onClick = onBackPressed,
            icon = com.radlance.uikit.R.drawable.ic_back,
            state = BubbleButtonState.Small,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(
                    top = CustomTheme.elevation.spacing24dp,
                    start = CustomTheme.elevation.spacing20dp
                )
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = CustomTheme.elevation.spacing20dp)
        ) {
            Text(
                text = stringResource(R.string.enter_code_from_telegram),
                style = CustomTheme.typography.title3SemiBold
            )
            Spacer(Modifier.height(CustomTheme.elevation.spacing24dp))
            Row(horizontalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp)) {
                repeat(4) { index ->
                    SingleInputField(
                        value = otpList[index],
                        onValueChange = {
                            if (it.length < 2 && it.isDigitsOnly()) viewModel.enterValue(it, index)
                        }
                    )
                }
            }
            Spacer(Modifier.height(CustomTheme.elevation.spacing16dp))

            val text = if (isTimerRunning) {
                val resendCodeTimer = stringResource(R.string.resend_code_timer, countdown)
                "$resendCodeTimer ${countdown.formatSeconds()}"
            } else {
                stringResource(R.string.resend_code)
            }

            val textColor = if (isTimerRunning) {
                CustomTheme.colors.placeholder
            } else {
                CustomTheme.colors.accent
            }


            Text(
                text = text,
                style = CustomTheme.typography.textRegular.copy(
                    color = textColor,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .padding(horizontal = 46.dp)
                    .then(
                        if (!isTimerRunning) {
                            Modifier.clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple()
                            ) { isTimerRunning = true }
                        } else {
                            Modifier
                        }
                    )
            )
        }
    }
}

private fun Int.formatSeconds(): String {
    return when {
        this % 100 in 11..14 -> "секунд"
        this % 10 == 1 -> "секунду"
        this % 10 in 2..4 -> "секунды"
        else -> "секунд"
    }
}