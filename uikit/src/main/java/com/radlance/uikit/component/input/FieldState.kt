package com.radlance.uikit.component.input

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

interface FieldState {

    fun horizontalArrangement(): Arrangement.Horizontal

    fun alignment(): Alignment

    fun spacerWidth(): Dp

    fun textAlign(): TextAlign

    object Base : FieldState {
        override fun horizontalArrangement(): Arrangement.Horizontal = Arrangement.Start

        override fun alignment(): Alignment = Alignment.CenterStart

        override fun spacerWidth(): Dp = 14.dp

        override fun textAlign(): TextAlign = TextAlign.Start
    }

    object Single : FieldState {
        override fun horizontalArrangement(): Arrangement.Horizontal = Arrangement.Center

        override fun alignment(): Alignment = Alignment.Center

        override fun spacerWidth(): Dp = 0.dp

        override fun textAlign(): TextAlign = TextAlign.Center
    }
}