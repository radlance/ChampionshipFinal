package com.radlance.uikit.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BubbleButton(
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier,
    state: BubbleButtonState = BubbleButtonState.Default
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .size(state.size())
            .clip(RoundedCornerShape(state.radius()))
            .background(CustomTheme.colors.inputBg)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = CustomTheme.colors.description
        )
    }
}