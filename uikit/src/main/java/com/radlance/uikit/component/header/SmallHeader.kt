package com.radlance.uikit.component.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.radlance.uikit.theme.CustomTheme

@Composable
fun SmallHeader(
    title: String,
    modifier: Modifier = Modifier,
    startContent: @Composable () -> Unit = {},
    endContent: @Composable () -> Unit = {}
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        Box(modifier = Modifier.align(Alignment.CenterStart)) {
            startContent()
        }

        Text(
            text = title,
            style = CustomTheme.typography.title2SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Box(modifier = Modifier.align(Alignment.CenterEnd)) {
            endContent()
        }
    }
}