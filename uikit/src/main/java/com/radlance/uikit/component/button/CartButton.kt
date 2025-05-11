package com.radlance.uikit.component.button

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.theme.CustomTheme

@Composable
fun CartButton(
    onClick: () -> Unit,
    label: String,
    totalPrice: Int,
    modifier: Modifier = Modifier
) {
    val buttonState = ButtonState.Big

    Box(contentAlignment = Alignment.Center) {
        Button(
            onClick = onClick,
            modifier = modifier
                .then(buttonState.appButtonWidth())
                .height(buttonState.height()),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = CustomTheme.colors.accent,
                disabledContainerColor = CustomTheme.colors.accentInactive
            )
        ) {}
        Row(modifier = modifier.fillMaxWidth()) {
            Spacer(Modifier.width(16.dp))
            Icon(
                painter = painterResource(R.drawable.ic_cart),
                contentDescription = "ic_cart",
                tint = CustomTheme.colors.white
            )
            Spacer(Modifier.width(16.dp))
            Text(
                text = label,
                style = buttonState.style().copy(color = CustomTheme.colors.white)
            )
            Spacer(Modifier.weight(1f))
            Text(
                text = "$totalPrice ₽",
                style = buttonState.style().copy(color = CustomTheme.colors.white)
            )
            Spacer(Modifier.width(16.dp))
        }
    }
}