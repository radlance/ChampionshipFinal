package com.radlance.uikit.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp

@Immutable
data class CustomColors(
    val accent: Color,
    val accentInactive: Color,
    val black: Color,
    val white: Color,
    val error: Color,
    val success: Color,
    val inputBg: Color,
    val inputStroke: Color,
    val inputIcon: Color,
    val placeholder: Color,
    val description: Color,
    val cardStroke: Color
)

@Immutable
data class CustomTypography(
    val title1SemiBold: TextStyle,
    val title1Heavy: TextStyle,
    val title2Regular: TextStyle,
    val title2SemiBold: TextStyle,
    val title2Heavy: TextStyle,
    val title3Regular: TextStyle,
    val title3Medium: TextStyle,
    val title3SemiBold: TextStyle,
    val headlineRegular: TextStyle,
    val headlineMedium: TextStyle,
    val textRegular: TextStyle,
    val textMedium: TextStyle,
    val captionRegular: TextStyle,
    val captionSemiBold: TextStyle,
    val caption2Regular: TextStyle,
    val caption2Bold: TextStyle
)

@Immutable
data class CustomElevation(
    val spacing4dp: Dp,
    val spacing8dp: Dp,
    val spacing12dp: Dp,
    val spacing16dp: Dp,
    val spacing20dp: Dp,
    val spacing24dp: Dp,
    val spacing32dp: Dp,
    val spacing40dp: Dp,
    val spacing48dp: Dp,
    val spacing56dp: Dp,
    val spacing64dp: Dp
)

val LocalCustomColors = staticCompositionLocalOf {
    CustomColors(
        accent = Color.Unspecified,
        accentInactive = Color.Unspecified,
        black = Color.Unspecified,
        white = Color.Unspecified,
        error = Color.Unspecified,
        success = Color.Unspecified,
        inputBg = Color.Unspecified,
        inputStroke = Color.Unspecified,
        inputIcon = Color.Unspecified,
        placeholder = Color.Unspecified,
        description = Color.Unspecified,
        cardStroke = Color.Unspecified
    )
}

val LocalCustomTypography = staticCompositionLocalOf {
    CustomTypography(
        TextStyle.Default,
        title1Heavy = TextStyle.Default,
        title2Regular = TextStyle.Default,
        title2SemiBold = TextStyle.Default,
        title2Heavy = TextStyle.Default,
        title3Regular = TextStyle.Default,
        title3Medium = TextStyle.Default,
        title3SemiBold = TextStyle.Default,
        headlineRegular = TextStyle.Default,
        headlineMedium = TextStyle.Default,
        textRegular = TextStyle.Default,
        textMedium = TextStyle.Default,
        captionRegular = TextStyle.Default,
        captionSemiBold = TextStyle.Default,
        caption2Regular = TextStyle.Default,
        caption2Bold = TextStyle.Default,
    )
}

val LocalCustomElevation = staticCompositionLocalOf {
    CustomElevation(
        spacing4dp = Dp.Unspecified,
        spacing8dp = Dp.Unspecified,
        spacing12dp = Dp.Unspecified,
        spacing16dp = Dp.Unspecified,
        spacing20dp = Dp.Unspecified,
        spacing24dp = Dp.Unspecified,
        spacing32dp = Dp.Unspecified,
        spacing40dp = Dp.Unspecified,
        spacing48dp = Dp.Unspecified,
        spacing56dp = Dp.Unspecified,
        spacing64dp = Dp.Unspecified
    )
}

@Composable
fun CustomTheme(content: @Composable () -> Unit) {
    val customColors = CustomColors(
        accent = Accent,
        accentInactive = AccentInactive,
        black = Black,
        white = White,
        error = Error,
        success = Success,
        inputBg = InputBg,
        inputStroke = InputStroke,
        inputIcon = InputIcon,
        placeholder = Placeholder,
        description = Description,
        cardStroke = CardStroke
    )


    val customTypography = CustomTypography(
        title1SemiBold = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W600,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.0033.em,
            color = Black
        ),
        title1Heavy = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W900,
            fontSize = 24.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.0033.em,
            color = Black
        ),
        title2Regular = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.0038.em,
            color = Black
        ),
        title2SemiBold = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W600,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.0038.em,
            color = Black
        ),
        title2Heavy = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W900,
            fontSize = 20.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.0038.em,
            color = Black
        ),
        title3Regular = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        title3Medium = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W500,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        title3SemiBold = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W600,
            fontSize = 17.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        headlineRegular = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.0032).em,
            color = Black
        ),
        headlineMedium = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W500,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            letterSpacing = (-0.0032).em,
            color = Black
        ),
        textRegular = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        textMedium = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W500,
            fontSize = 15.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        captionRegular = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        captionSemiBold = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W600,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        caption2Regular = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W400,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.em,
            color = Black
        ),
        caption2Bold = TextStyle(
            fontFamily = SfProDisplayFamily,
            fontWeight = FontWeight.W700,
            fontSize = 12.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.em,
            color = Black
        )
    )

    val customElevation = CustomElevation(
        spacing4dp = 4.dp,
        spacing8dp = 8.dp,
        spacing12dp = 12.dp,
        spacing16dp = 16.dp,
        spacing20dp = 20.dp,
        spacing24dp = 24.dp,
        spacing32dp = 32.dp,
        spacing40dp = 40.dp,
        spacing48dp = 48.dp,
        spacing56dp = 56.dp,
        spacing64dp = 64.dp
    )

    CompositionLocalProvider(
        LocalCustomColors provides customColors,
        LocalCustomTypography provides customTypography,
        LocalCustomElevation provides customElevation,
        content = content
    )
}

object CustomTheme {
    val colors: CustomColors
        @Composable
        get() = LocalCustomColors.current

    val typography: CustomTypography
        @Composable
        get() = LocalCustomTypography.current

    val elevation: CustomElevation
        @Composable
        get() = LocalCustomElevation.current
}