package com.radlance.uikit.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.theme.CustomTheme
import java.time.LocalDate
import java.time.temporal.ChronoUnit

@Composable
fun ProjectCard(
    title: String,
    startDate: LocalDate,
    endDate: LocalDate?,
    onProjectClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val daysPassed = ChronoUnit.DAYS.between(startDate, endDate ?: LocalDate.now())

    val dayWord = when {
        daysPassed % 100 in 11..19 -> "дней"
        daysPassed % 10 == 1L -> "день"
        daysPassed % 10 in 2..4 -> "дня"
        else -> "дней"
    }

    val verb = if (daysPassed == 1L) "прошел" else "прошло"

    PrimaryCardBackground {
        Column(modifier = modifier) {
            Text(
                text = title,
                style = CustomTheme.typography.headlineMedium
            )
            Spacer(Modifier.height(44.dp))
            Row(verticalAlignment = Alignment.Bottom) {
                Text(
                    text = "$verb $daysPassed $dayWord",
                    style = CustomTheme.typography.captionSemiBold.copy(
                        color = CustomTheme.colors.placeholder
                    )
                )

                Spacer(Modifier.weight(1f))

                AppButton(
                    onClick = onProjectClick,
                    label = stringResource(R.string.open),
                    buttonState = ButtonState.Small
                )
            }
        }
    }
}