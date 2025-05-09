package com.radlance.uikit.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.button.OutlinedAppButton
import com.radlance.uikit.theme.CustomTheme

@Composable
fun PrimaryCard(
    title: String,
    category: String,
    price: Int,
    inCart: Boolean,
    modifier: Modifier = Modifier
) {
    PrimaryCardBackground {
        Column(modifier = modifier) {
            Text(
                text = title,
                style = CustomTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(16.dp))
            Row {
                Column {
                    Text(
                        text = category,
                        style = CustomTheme.typography.captionSemiBold.copy(
                            color = CustomTheme.colors.placeholder
                        )
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "$price ₽",
                        style = CustomTheme.typography.title3SemiBold
                    )
                }
                Spacer(Modifier.weight(1f))
                if (inCart) {
                    OutlinedAppButton(
                        onClick = {},
                        label = stringResource(R.string.remove),
                        buttonState = ButtonState.Small
                    )
                } else {
                    AppButton(
                        onClick = {},
                        label = stringResource(R.string.add),
                        buttonState = ButtonState.Small
                    )
                }
            }
        }
    }
}