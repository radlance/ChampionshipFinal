package com.radlance.uikit.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun AppButton(
    onClick: () -> Unit,
    label: String,
    buttonState: ButtonState,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Box(contentAlignment = Alignment.Center) {
        Button(
            onClick = onClick,
            enabled = enabled,
            modifier = modifier
                .then(buttonState.appButtonWidth())
                .height(buttonState.height()),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = CustomTheme.colors.accent,
                disabledContainerColor = CustomTheme.colors.accentInactive
            )
        ) {}

        Text(
            text = label,
            style = buttonState.style().copy(color = CustomTheme.colors.white)
        )
    }
}