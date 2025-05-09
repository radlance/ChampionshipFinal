package com.radlance.championshipfinal.presentation.auth

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun PasswordCircle(
    filled: Boolean,
    modifier: Modifier = Modifier
) {
    val fillColor by animateColorAsState(
        if (filled) CustomTheme.colors.accent else Color.Transparent
    )
    Box(
        modifier = modifier
            .size(CustomTheme.elevation.spacing16dp)
            .clip(CircleShape)
            .background(fillColor)
            .border(width = 1.dp, color = CustomTheme.colors.accent, shape = CircleShape)
    )
}