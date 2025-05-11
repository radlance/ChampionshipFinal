package com.radlance.championshipfinal.presentation.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radlance.championshipfinal.domain.home.Sale
import com.radlance.uikit.theme.CustomTheme

@Composable
fun SaleCard(
    sale: Sale,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(width = 270.dp, height = 152.dp)
            .clip(RoundedCornerShape(CustomTheme.elevation.spacing12dp))
            .background(sale.backgroundBrush)
    ) {
        Image(
            painter = painterResource(sale.imageResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxHeight()
                .align(Alignment.CenterEnd)
                .offset(sale.offset)
                .scale(sale.scale)

        )
        Row {
            Column(
                modifier = Modifier
                    .padding(
                        start = CustomTheme.elevation.spacing16dp,
                        top = CustomTheme.elevation.spacing16dp,
                        bottom = CustomTheme.elevation.spacing16dp
                    )
                    .weight(2f)
            ) {
                Text(
                    text = sale.title,
                    style = CustomTheme.typography.title2Heavy.copy(color = CustomTheme.colors.white),
                    modifier = Modifier.padding(end = CustomTheme.elevation.spacing16dp)
                )

                Spacer(Modifier.weight(1f))
                Text(
                    text = "${sale.price} ₽",
                    style = CustomTheme.typography.title2Heavy.copy(
                        color = CustomTheme.colors.white
                    )
                )
            }
            Box(modifier = Modifier.weight(1f))
        }
    }
}