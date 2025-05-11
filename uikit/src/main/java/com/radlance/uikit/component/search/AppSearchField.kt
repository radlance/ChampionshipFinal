package com.radlance.uikit.component.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.theme.CustomTheme

@Composable
fun AppSearchField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
    additionalContent: @Composable () -> Unit = {}
) {
    val interactionSource = remember { MutableInteractionSource() }
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .height(CustomTheme.elevation.spacing48dp)
                .clip(RoundedCornerShape(10.dp))
                .background(CustomTheme.colors.inputBg)
                .border(
                    width = 1.dp,
                    color = CustomTheme.colors.inputStroke,
                    shape = RoundedCornerShape(10.dp)
                )
                .weight(1f)
        ) {
            Spacer(Modifier.width(14.dp))
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "ic_search",
                tint = CustomTheme.colors.description
            )
            Spacer(Modifier.width(CustomTheme.elevation.spacing8dp))
            Box(modifier = Modifier.weight(1f)) {
                if (value.isEmpty()) {
                    Text(
                        text = hint,
                        style = CustomTheme.typography.headlineRegular,
                        color = CustomTheme.colors.placeholder
                    )
                }
                BasicTextField(
                    value = value,
                    singleLine = true,
                    cursorBrush = SolidColor(CustomTheme.colors.accent),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions(onSearch = { keyboardController?.hide() }),
                    onValueChange = onValueChange,
                    textStyle = CustomTheme.typography.textRegular,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            if (value.isNotEmpty()) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = "ic_close",
                    tint = CustomTheme.colors.description,
                    modifier = Modifier.clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        onValueChange("")
                    }
                )
                Spacer(Modifier.width(14.dp))
            }
        }
        additionalContent()

    }
}