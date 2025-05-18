package com.radlance.uikit.component.button

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.theme.CustomTheme

interface BubbleButtonState {

    fun size(): Dp

    fun radius(): Dp

    object Default : BubbleButtonState {
        override fun size(): Dp = 48.dp

        override fun radius(): Dp = 8.dp
    }

    object Small : BubbleButtonState {
        override fun size(): Dp = 32.dp

        override fun radius(): Dp = 10.dp
    }
}

@Preview(showBackground = true)
@Composable
private fun BubbleButtonSmallPreview() {
    CustomTheme {
        BubbleButton(
            onClick = {},
            icon = R.drawable.ic_back,
            state = BubbleButtonState.Small,
            modifier = Modifier.padding(5.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun BubbleButtonBigPreview() {
    CustomTheme {
        BubbleButton(
            onClick = {},
            icon = R.drawable.ic_filter,
            state = BubbleButtonState.Default,
            modifier = Modifier.padding(15.dp)
        )
    }
}