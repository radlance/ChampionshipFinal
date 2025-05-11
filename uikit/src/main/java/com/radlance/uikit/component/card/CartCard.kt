package com.radlance.uikit.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.component.button.ChangeQuantityButton
import com.radlance.uikit.theme.CustomTheme

@Composable
fun CartCard(
    title: String,
    price: Int,
    quantity: Int,
    onChangeQuantity: (Int, increment: Boolean) -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    PrimaryCardBackground(modifier = modifier) {
        Column {
            Row {
                Text(
                    text = title,
                    style = CustomTheme.typography.headlineMedium,
                    modifier = Modifier.weight(5f)
                )

                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.TopEnd) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = null,
                        tint = CustomTheme.colors.description,
                        modifier = Modifier
                            .clip(CircleShape)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple(),
                                onClick = onRemove
                            )
                    )
                }
            }
            Spacer(Modifier.height(34.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "$price ₽", style = CustomTheme.typography.title3Medium)
                Spacer(Modifier.weight(2f))
                Text(
                    text = stringResource(R.string.cart_quantity, quantity),
                    style = CustomTheme.typography.textRegular
                )
                Spacer(Modifier.width(42.dp))
                ChangeQuantityButton(
                    quantity = quantity,
                    onDecrementClick = { onChangeQuantity(it, false) },
                    onIncrementClick = { onChangeQuantity(it, true) }
                )
            }
        }
    }
}