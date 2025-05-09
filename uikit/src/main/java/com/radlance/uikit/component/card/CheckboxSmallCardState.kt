package com.radlance.uikit.component.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.radlance.uikit.theme.CustomTheme

interface CheckboxSmallCardState {

    @Composable
    fun textColor(): Color

    object Enabled : CheckboxSmallCardState {

        @Composable
        override fun textColor(): Color = CustomTheme.colors.black
    }

    object Disabled : CheckboxSmallCardState {

        @Composable
        override fun textColor(): Color = CustomTheme.colors.inputIcon
    }
}