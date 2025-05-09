package com.radlance.uikit.component.button

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

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