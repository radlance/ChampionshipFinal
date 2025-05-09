package com.radlance.uikit.component.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun EnterInputField(
    value: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    enabled: Boolean,
    label: String,
    hint: String,
    modifier: Modifier = Modifier,
    fieldState: FieldState = FieldState.Base
) {

    var hasFocus by rememberSaveable { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    val borderColor by animateColorAsState(
        when {
            value.isNotEmpty() && !hasFocus -> CustomTheme.colors.inputIcon
            hasFocus && errorMessage.isEmpty() -> CustomTheme.colors.accent
            errorMessage.isNotEmpty() -> CustomTheme.colors.error
            else -> CustomTheme.colors.inputStroke
        }
    )

    val borderAlpha by animateFloatAsState(
        if (hasFocus && errorMessage.isEmpty()) 0.5f else 1f
    )

    val hintColor by animateColorAsState(
        if (enabled) CustomTheme.colors.placeholder else CustomTheme.colors.inputIcon
    )

    val fillColor by animateColorAsState(
        if (errorMessage.isNotEmpty()) {
            CustomTheme.colors.error.copy(alpha = 0.1f)
        } else {
            Color.Transparent
        }
    )

    Column(modifier = modifier) {
        if (label.isNotBlank()) {
            Text(
                text = label,
                color = CustomTheme.colors.description,
                style = CustomTheme.typography.captionRegular
            )
            Spacer(Modifier.height(8.dp))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(CustomTheme.elevation.spacing48dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CustomTheme.colors.inputBg)
                .border(
                    width = 1.dp,
                    color = borderColor.copy(alpha = borderAlpha),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(fillColor)
            )

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = fieldState.horizontalArrangement(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(Modifier.width(fieldState.spacerWidth()))
                Box(contentAlignment = fieldState.alignment()) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            style = CustomTheme.typography.captionRegular,
                            color = hintColor
                        )
                    }
                    BasicTextField(
                        value = value,
                        singleLine = true,
                        cursorBrush = SolidColor(CustomTheme.colors.accent),
                        onValueChange = onValueChange,
                        textStyle = CustomTheme.typography.textRegular.copy(
                            textAlign = fieldState.textAlign()
                        ),
                        enabled = enabled,
                        modifier = Modifier
                            .focusRequester(focusRequester)
                            .onFocusChanged { focusState -> hasFocus = focusState.hasFocus }
                    )
                }
            }
        }

        AnimatedVisibility(visible = errorMessage.isNotEmpty()) {
            Column {
                Spacer(Modifier.height(8.dp))
                Text(
                    text = errorMessage,
                    color = CustomTheme.colors.error,
                    style = CustomTheme.typography.captionRegular
                )
            }
        }
    }
}