package com.radlance.uikit.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun AppSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    checkedTrackColor: Color = CustomTheme.colors.accent,
    uncheckedTrackColor: Color = CustomTheme.colors.inputStroke,
    thumbColor: Color = Color.White
) {
    val thumbPosition by animateFloatAsState(targetValue = if (checked) 1f else 0f)
    val circleRadius = remember { 13.5.dp }
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(width = 51.dp, height = 31.dp)
            .background(color = Color.Transparent)
            .clickable(
                onClick = { onCheckedChange(!checked) },
                interactionSource = interactionSource,
                indication = null
            )
    ) {
        val trackColor by animateColorAsState(
            if (checked) checkedTrackColor else uncheckedTrackColor
        )

        Canvas(modifier = Modifier.matchParentSize()) {
            drawRoundRect(
                color = trackColor,
                size = Size(size.width, size.height),
                cornerRadius = CornerRadius(x = 18.dp.toPx(), y = 18.dp.toPx())
            )

            val thumbOffset = lerp(
                start = 16.dp.toPx(),
                stop = size.width - 16.dp.toPx(),
                fraction = thumbPosition
            )

            drawCircle(
                color = thumbColor,
                radius = circleRadius.toPx(),
                center = Offset(x = thumbOffset, y = size.height / 2)
            )
        }
    }
}