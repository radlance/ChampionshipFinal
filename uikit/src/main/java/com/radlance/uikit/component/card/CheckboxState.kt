package com.radlance.uikit.component.card

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.radlance.uikit.R
import com.radlance.uikit.theme.CustomTheme

interface CheckboxState {

    @Composable
    fun borderColor(): Color

    @Composable
    fun backgroundColor(): Color

    @Composable
    fun Content()

    object Checked : CheckboxState {

        @Composable
        override fun borderColor(): Color = CustomTheme.colors.accent

        @Composable
        override fun backgroundColor(): Color = CustomTheme.colors.accent

        @Composable
        override fun Content() {
            Icon(
                painter = painterResource(R.drawable.ic_check),
                contentDescription = "ic_check",
                tint = CustomTheme.colors.white
            )
        }
    }

    object Unchecked : CheckboxState {

        @Composable
        override fun borderColor(): Color = CustomTheme.colors.inputStroke

        @Composable
        override fun backgroundColor(): Color = CustomTheme.colors.inputBg

        @Composable
        override fun Content() = Unit
    }
}