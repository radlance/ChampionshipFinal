package com.radlance.uikit.component.header

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.radlance.uikit.R
import com.radlance.uikit.component.button.BubbleButton
import com.radlance.uikit.component.button.BubbleButtonState
import com.radlance.uikit.theme.CustomTheme

@Composable
fun SmallHeader(
    title: String,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {

        BubbleButton(
            icon = R.drawable.ic_back,
            state = BubbleButtonState.Small,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        Text(text = title, style = CustomTheme.typography.title2SemiBold)
        Icon(
            painter = painterResource(R.drawable.ic_trash),
            contentDescription = "ic_trash",
            tint = CustomTheme.colors.inputIcon,
            modifier = Modifier.align(Alignment.CenterEnd)
        )
    }
}