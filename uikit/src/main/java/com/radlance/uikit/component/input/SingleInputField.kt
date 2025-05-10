package com.radlance.uikit.component.input

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun SingleInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    hint: String = ""
) {

    EnterInputField(
        value = value,
        onValueChange = onValueChange,
        errorMessage = "",
        enabled = true,
        label = "",
        hint = hint,
        fieldState = FieldState.Single,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = modifier.size(48.dp)
    )
}