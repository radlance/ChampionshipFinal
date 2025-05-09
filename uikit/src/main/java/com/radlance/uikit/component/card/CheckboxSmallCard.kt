package com.radlance.uikit.component.card

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun CheckboxSmallCard(
    title: String,
    price: Int,
    checked: Boolean,
    onChecked: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val state = if (checked) CheckboxSmallCardState.Enabled else CheckboxSmallCardState.Disabled
    val color by animateColorAsState(state.textColor())

    Column(modifier = modifier.fillMaxWidth()) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppCheckbox(checked, onChecked)
            Spacer(Modifier.width(CustomTheme.elevation.spacing16dp))
            Text(
                text = title,
                style = CustomTheme.typography.caption2Regular.copy(color = color)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "$price ₽",
                style = CustomTheme.typography.textRegular.copy(color = color)
            )
        }
        Spacer(Modifier.height(CustomTheme.elevation.spacing8dp))
        HorizontalDivider(thickness = 1.dp, color = CustomTheme.colors.cardStroke)
    }
}