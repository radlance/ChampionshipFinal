package com.radlance.uikit.component.card

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.radlance.uikit.theme.CustomTheme

interface BaseSmallCardState {

    @Composable
    fun textColor(): Color

    @Composable
    fun borderColor(): Color

    object Active : BaseSmallCardState {

        @Composable
        override fun textColor(): Color = CustomTheme.colors.black

        @Composable
        override fun borderColor(): Color = CustomTheme.colors.description
    }

    object Inactive : BaseSmallCardState {

        @Composable
        override fun textColor(): Color = CustomTheme.colors.inputIcon

        @Composable
        override fun borderColor(): Color = CustomTheme.colors.inputIcon
    }
}