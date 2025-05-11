package com.radlance.uikit.component.card

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.button.OutlinedAppButton
import com.radlance.uikit.theme.CustomTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrimaryCard(
    title: String,
    category: String,
    price: Int,
    description: String,
    materialCost: String,
    inCart: Boolean,
    onCartClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()

    if (showBottomSheet) {
        AppModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {
            Column {
                Text(text = title, style = CustomTheme.typography.title2SemiBold)
                Spacer(Modifier.height(CustomTheme.elevation.spacing20dp))
                Text(
                    text = stringResource(R.string.description),
                    style = CustomTheme.typography.headlineMedium.copy(
                        color = CustomTheme.colors.placeholder
                    )
                )
                Spacer(Modifier.height(CustomTheme.elevation.spacing8dp))
                Text(text = description, style = CustomTheme.typography.textRegular)
                Spacer(Modifier.height(89.dp))
                Text(
                    text = stringResource(R.string.approximate_cost),
                    style = CustomTheme.typography.captionSemiBold.copy(
                        color = CustomTheme.colors.placeholder
                    )
                )
                Spacer(Modifier.height(CustomTheme.elevation.spacing4dp))
                Text(text = materialCost, style = CustomTheme.typography.headlineMedium)
                Spacer(Modifier.height(19.dp))

                if (inCart) {
                    OutlinedAppButton(
                        onClick = onCartClick,
                        label = stringResource(R.string.remove),
                        buttonState = ButtonState.Big
                    )
                } else {
                    AppButton(
                        onClick = onCartClick,
                        label = stringResource(R.string.add_by, price),
                        buttonState = ButtonState.Big
                    )
                }
                Spacer(Modifier.height(CustomTheme.elevation.spacing40dp))
            }
        }
    }

    PrimaryCardBackground(
        modifier = Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = ripple(),
            onClick = { showBottomSheet = true }
        )
    ) {
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
                        onClick = onCartClick,
                        label = stringResource(R.string.remove),
                        buttonState = ButtonState.Small
                    )
                } else {
                    AppButton(
                        onClick = onCartClick,
                        label = stringResource(R.string.add),
                        buttonState = ButtonState.Small
                    )
                }
            }
        }
    }
}