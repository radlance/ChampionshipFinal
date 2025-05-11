package com.radlance.uikit.component.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun OutlinedAppButton(
    onClick: () -> Unit,
    label: String,
    buttonState: ButtonState,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .then(buttonState.appButtonWidth())
            .height(buttonState.height()),
        border = BorderStroke(
            width = 1.dp,
            color = CustomTheme.colors.accent
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Text(
            text = label,
            style = buttonState.style().copy(color = CustomTheme.colors.accent)
        )
    }
}

