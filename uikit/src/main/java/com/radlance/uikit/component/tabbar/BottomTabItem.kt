package com.radlance.uikit.component.tabbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BottomTabItem(
    bottomTab: BottomTab,
    selected: Boolean,
    onTabClick: (BottomTab) -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }
    val color by animateColorAsState(
        if (selected) CustomTheme.colors.accent else CustomTheme.colors.inputIcon
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.clickable(
            interactionSource = interactionSource,
            indication = null
        ) { onTabClick(bottomTab) }
    ) {
        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center) {
            Icon(
                painter = painterResource(bottomTab.iconResId()),
                contentDescription = null,
                tint = color
            )
        }
        Text(
            text = stringResource(bottomTab.labelResId()),
            style = CustomTheme.typography.caption2Regular.copy(color = color)
        )
    }
}