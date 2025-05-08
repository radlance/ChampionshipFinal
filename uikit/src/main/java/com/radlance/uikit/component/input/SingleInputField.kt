package com.radlance.uikit.component.input

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SingleInputField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {

    EnterInputField(
        value = value,
        onValueChange = onValueChange,
        errorMessage = "",
        enabled = true,
        label = "",
        hint = hint,
        fieldState = FieldState.Single,
        modifier = modifier.size(48.dp)
    )
}