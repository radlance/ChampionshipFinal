package com.radlance.championshipfinal.presentation.auth

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun PasswordEnterButton(
    number: Int,
    onClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var isPressed by remember { mutableStateOf(false) }

    val numberColor by animateColorAsState(
        if (isPressed) CustomTheme.colors.white else CustomTheme.colors.black
    )

    val color by animateColorAsState(
        if (isPressed) CustomTheme.colors.accent else CustomTheme.colors.inputBg,
    )


    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(80.dp)
            .clip(CircleShape)
            .background(color)
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = {
                        isPressed = true
                        tryAwaitRelease()
                        onClick(number)
                        isPressed = false
                    }
                )
            }

    ) {
        Text(
            text = number.toString(),
            style = CustomTheme.typography.title1SemiBold.copy(color = numberColor)
        )
    }
}