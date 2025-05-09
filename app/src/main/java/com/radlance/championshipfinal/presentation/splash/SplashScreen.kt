package com.radlance.championshipfinal.presentation.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.radlance.championshipfinal.R
import com.radlance.uikit.theme.CustomTheme
import com.radlance.uikit.theme.SfProDisplayFamily
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onDelayFinished: () -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        delay(500)
        onDelayFinished()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFFA1CAFF),
                        Color(0xFF4D9CFF),
                        Color(0xFFA1CAFF)
                    )
                ),
            )
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color(0xFF74C8E4),
                        Color(0xFF73D5BC),
                        Color(0xFF74C8E4)
                    )
                )
            )
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6269F0).copy(alpha = 0.05f),
                        Color(0xFF3740F5).copy(alpha = 0.65f),
                        Color(0xFF2254F5),
                        Color(0xFF3740F5).copy(alpha = 0.65f),
                        Color(0xFF6269F0).copy(alpha = 0.05f),
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.matule),
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 40.sp,
            lineHeight = 64.sp,
            letterSpacing = 1.04.sp,
            color = CustomTheme.colors.white
        )
    }
}