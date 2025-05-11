package com.radlance.uikit.component.button

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@SuppressLint("ModifierFactoryExtensionFunction")
interface ButtonState {

    fun height(): Dp

    fun appButtonWidth(): Modifier

    @Composable
    fun style(): TextStyle

    object Big : ButtonState {

        override fun height(): Dp = 56.dp

        override fun appButtonWidth(): Modifier = Modifier.fillMaxWidth()

        @Composable
        override fun style(): TextStyle = CustomTheme.typography.title3SemiBold
    }

    object Medium : ButtonState {

        override fun height(): Dp = 48.dp

        override fun appButtonWidth(): Modifier = Modifier.fillMaxWidth()

        @Composable
        override fun style(): TextStyle = CustomTheme.typography.textRegular
    }

    object Small : ButtonState {

        override fun height(): Dp = 40.dp

        override fun appButtonWidth(): Modifier = Modifier

        @Composable
        override fun style(): TextStyle = CustomTheme.typography.captionSemiBold
    }

    object Chips : ButtonState {

        override fun height(): Dp = 48.dp

        override fun appButtonWidth(): Modifier = Modifier

        @Composable
        override fun style(): TextStyle = CustomTheme.typography.textMedium
    }

    object Login : ButtonState {
        override fun height(): Dp = 60.dp

        override fun appButtonWidth(): Modifier = Modifier.fillMaxWidth()

        @Composable
        override fun style(): TextStyle = CustomTheme.typography.title3Medium
    }
}