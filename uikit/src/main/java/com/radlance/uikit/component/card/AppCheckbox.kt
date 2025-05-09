package com.radlance.uikit.component.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
    fun AppCheckbox(
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val state = if (checked) CheckboxState.Checked else CheckboxState.Unchecked

    val backgroundColor by animateColorAsState(state.backgroundColor())
    val borderColor by animateColorAsState(state.borderColor())

    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(20.dp)
            .clip(RoundedCornerShape(CustomTheme.elevation.spacing4dp))
            .background(backgroundColor)
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(CustomTheme.elevation.spacing4dp)
            )
            .clickable(interactionSource = interactionSource, indication = null) {
                onChecked(!checked)
            }
    ) {
        state.Content()
    }
}