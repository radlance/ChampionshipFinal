package com.radlance.uikit.component.button

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ChangeQuantityButton(
    quantity: Int,
    onDecrementClick: (Int) -> Unit,
    onIncrementClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    Box(
        modifier = modifier
            .size(width = 64.dp, height = 32.dp)
            .clip(RoundedCornerShape(CustomTheme.elevation.spacing8dp))
            .background(CustomTheme.colors.inputBg),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.padding(6.dp), verticalAlignment = Alignment.CenterVertically) {

            val decrementIconColor by animateColorAsState(
                if (quantity > 1) CustomTheme.colors.placeholder else CustomTheme.colors.inputIcon
            )

            Icon(
                painter = painterResource(R.drawable.ic_minus),
                contentDescription = "ic_minus",
                tint = decrementIconColor,
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        if (quantity > 1) {
                            onDecrementClick(quantity.dec())
                        }
                    }
            )

            VerticalDivider(
                thickness = 1.dp,
                modifier = Modifier
                    .height(16.dp)
                    .padding(horizontal = 6.dp),
                color = CustomTheme.colors.inputStroke
            )

            val incrementIconColor by animateColorAsState(
                CustomTheme.colors.placeholder
            )

            Icon(
                painter = painterResource(R.drawable.ic_plus),
                contentDescription = "ic_plus",
                tint = incrementIconColor,
                modifier = Modifier.clickable(interactionSource, indication = null) {
                    onIncrementClick(quantity.inc())
                }
            )
        }
    }
}