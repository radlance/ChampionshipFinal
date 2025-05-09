package com.radlance.uikit.component.header

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.radlance.uikit.component.button.BubbleButton
import com.radlance.uikit.component.button.BubbleButtonState
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BigHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        BubbleButton(icon = R.drawable.ic_back, state = BubbleButtonState.Small)
        Spacer(Modifier.height(24.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = title, style = CustomTheme.typography.title1Heavy)
            Spacer(Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_trash),
                contentDescription = "ic_trash",
                tint = CustomTheme.colors.inputIcon
            )
        }
    }
}