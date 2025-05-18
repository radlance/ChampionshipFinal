package com.radlance.uikit.component.button

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
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

@Preview(showBackground = true)
@Composable
private fun BigButtonFirstPreview() {
    CustomTheme {
        AppButton(
            onClick = {},
            label = "Подтвердить",
            buttonState = ButtonState.Big,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BigButtonSecondPreview() {
    CustomTheme {
        AppButton(
            onClick = {},
            label = "Подтвердить",
            buttonState = ButtonState.Big,
            enabled = false,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BigButtonThirdPreview() {
    CustomTheme {
        OutlinedAppButton(
            onClick = {},
            label = "Подтвердить",
            buttonState = ButtonState.Big,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MediumAppButtonFirstPreview() {
    CustomTheme {
        AppButton(
            onClick = {},
            label = "Добавить заказчика",
            buttonState = ButtonState.Medium,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MediumAppButtonSecondPreview() {
    CustomTheme {
        OutlinedAppButton(
            onClick = {},
            label = "Добавить проект",
            buttonState = ButtonState.Medium,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MediumAppButtonThirdPreview() {
    CustomTheme {
        SecondaryButton(
            onClick = {},
            label = "Добавить проект",
            buttonState = ButtonState.Medium,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallAppButtonFirstPreview() {
    CustomTheme {
        AppButton(
            onClick = {},
            label = "Добавить",
            buttonState = ButtonState.Small,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallAppButtonSecondPreview() {
    CustomTheme {
        OutlinedAppButton(
            onClick = {},
            label = "Убрать",
            buttonState = ButtonState.Small,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallAppButtonThirdPreview() {
    CustomTheme {
        AppButton(
            onClick = {},
            label = "Добавить",
            enabled = false,
            buttonState = ButtonState.Small,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipsAppButtonSecondPreview() {
    CustomTheme {
        AppButton(
            onClick = {},
            label = "Популярное",
            buttonState = ButtonState.Small,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ChipsAppButtonThirdPreview() {
    CustomTheme {
        SecondaryButton(
            onClick = {},
            label = "Популярное",
            enabled = false,
            buttonState = ButtonState.Small,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CartButtonPreview() {
    CustomTheme {
        CartButton(
            onClick = {},
            label = "В корзину",
            totalPrice = 500,
            modifier = Modifier.padding(15.dp)
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginButtonFirstPreview() {
    CustomTheme {
        LoginButton(
            onClick = {},
            label = "Войти с VK",
            icon = R.drawable.ic_vk_login,
            modifier = Modifier.padding(15.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginButtonSecondPreview() {
    CustomTheme {
        LoginButton(
            onClick = {},
            label = "Войти с Yandex",
            icon = R.drawable.ic_yandex_login,
            modifier = Modifier.padding(15.dp)
        )
    }
}
