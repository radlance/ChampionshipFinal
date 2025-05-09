package com.radlance.uikit.component.button

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.theme.CustomTheme

@Composable
fun LoginButton(
    onClick: () -> Unit,
    label: String,
    @DrawableRes icon: Int,
    modifier: Modifier = Modifier
) {
    val buttonState = ButtonState.Login

    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .then(buttonState.appButtonWidth())
            .height(buttonState.height()),
        border = BorderStroke(
            width = 1.dp,
            color = CustomTheme.colors.inputStroke
        ),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(icon), contentDescription = null)
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = buttonState.style().copy(color = CustomTheme.colors.black)
            )
        }
    }
}