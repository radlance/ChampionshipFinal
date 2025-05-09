package com.radlance.uikit.component.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.theme.CustomTheme

@Composable
fun AnalysisCard(
    title: String,
    date: String,
    status: String,
    modifier: Modifier = Modifier
) {
    PrimaryCardBackground {
        Box(modifier = modifier) {
            Icon(
                painter = painterResource(R.drawable.ic_more),
                contentDescription = "ic_more",
                tint = CustomTheme.colors.black,
                modifier = Modifier.align(Alignment.TopEnd)
            )

            Icon(
                painter = painterResource(R.drawable.ic_download),
                contentDescription = "ic_download",
                tint = CustomTheme.colors.description,
                modifier = Modifier.align(Alignment.BottomEnd)
            )


            Column {
                Text(text = title, style = CustomTheme.typography.headlineMedium)
                Spacer(Modifier.height(CustomTheme.elevation.spacing4dp))
                Text(
                    text = date,
                    style = CustomTheme.typography.captionRegular.copy(
                        color = CustomTheme.colors.placeholder
                    )
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = status,
                    style = CustomTheme.typography.captionRegular.copy(
                        color = CustomTheme.colors.success
                    )
                )
            }
        }
    }
}