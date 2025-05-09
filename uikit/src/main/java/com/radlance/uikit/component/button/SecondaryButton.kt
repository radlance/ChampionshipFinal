package com.radlance.uikit.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun SecondaryButton(
    onClick: () -> Unit,
    label: String,
    buttonState: ButtonState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    val textColor by animateColorAsState(
        if (enabled) CustomTheme.colors.black else CustomTheme.colors.description
    )

    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .then(buttonState.appButtonWidth())
            .height(buttonState.height()),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = CustomTheme.colors.inputBg,
            disabledContainerColor = CustomTheme.colors.inputBg
        )
    ) {
        Text(
            text = label,
            style = buttonState.style().copy(color = textColor)
        )
    }
}