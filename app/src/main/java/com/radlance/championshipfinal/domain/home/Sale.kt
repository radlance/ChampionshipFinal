package com.radlance.championshipfinal.domain.home

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.IntOffset

data class Sale(
    val title: String,
    val price: Int,
    @DrawableRes val imageResId: Int,
    val backgroundBrush: Brush,
    val offset: Density.() -> IntOffset,
    val scale: Float = 1f
)