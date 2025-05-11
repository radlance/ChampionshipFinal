package com.radlance.championshipfinal.presentation.common

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun BaseColumn(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = CustomTheme.elevation.spacing20dp,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = modifier
            .safeDrawingPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = horizontalPadding)
    ) {
        content()
    }
}