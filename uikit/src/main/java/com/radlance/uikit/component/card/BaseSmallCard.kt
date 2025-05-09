package com.radlance.uikit.component.card

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BaseSmallCard(
    title: String,
    price: Int,
    modifier: Modifier = Modifier,
    state: BaseSmallCardState = BaseSmallCardState.Active
) {
    BaseSmallCardBackground(modifier = modifier, borderColor = state.borderColor()) {
        Row {
            Text(
                text = title,
                style = CustomTheme.typography.caption2Regular.copy(
                    color = state.textColor()
                ),
                modifier = Modifier.weight(2.5f)
            )
            Text(
                text = "$price ₽",
                style = CustomTheme.typography.textRegular.copy(
                    textAlign = TextAlign.End,
                    color = state.textColor()
                ),
                modifier = Modifier.weight(1f)
            )
        }
    }
}