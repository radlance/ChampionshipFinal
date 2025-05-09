package com.radlance.uikit.component.card

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun PrimaryCardBackground(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                spotColor = Color(0xFFE4EBF5).copy(alpha = 0.6f),
                shape = RoundedCornerShape(CustomTheme.elevation.spacing12dp),
                clip = false
            )
            .clip(RoundedCornerShape(CustomTheme.elevation.spacing12dp))
            .background(CustomTheme.colors.white)
            .border(
                width = 1.dp,
                color = CustomTheme.colors.cardStroke,
                shape = RoundedCornerShape(CustomTheme.elevation.spacing12dp)
            )

    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            content()
        }
    }
}