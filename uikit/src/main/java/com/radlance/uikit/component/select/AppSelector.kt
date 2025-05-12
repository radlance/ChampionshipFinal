package com.radlance.uikit.component.select

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.radlance.uikit.R
import com.radlance.uikit.component.card.AppModalBottomSheet
import com.radlance.uikit.theme.CustomTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppSelector(
    options: List<String>,
    selectedItem: String?,
    onItemSelect: (String?) -> Unit,
    hint: String,
    label: String,
    modifier: Modifier = Modifier,
    closable: Boolean = false,
    enabled: Boolean = true
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    var showBottomSheet by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    if (showBottomSheet) {
        AppModalBottomSheet(
            sheetState = sheetState,
            onDismissRequest = { showBottomSheet = false }
        ) {

            Column(modifier = Modifier) {
                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.heightIn(min = 40.dp)
                    ) {
                        Spacer(Modifier.width(24.dp))
                        Text(
                            text = option,
                            style = CustomTheme.typography.title2SemiBold,
                            modifier = Modifier
                                .weight(1f)
                                .clickable(
                                    interactionSource = remember { MutableInteractionSource() },
                                    indication = ripple()
                                ) {
                                    scope.launch {
                                        onItemSelect(option)
                                        sheetState.hide()
                                    }.invokeOnCompletion {
                                        if (!sheetState.isVisible) {
                                            showBottomSheet = false
                                        }
                                    }
                                }
                        )
                    }
                }
                Spacer(Modifier.height(CustomTheme.elevation.spacing64dp))
            }
        }
    }

    Column {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                style = CustomTheme.typography.captionRegular.copy(color = CustomTheme.colors.description)
            )
            Spacer(Modifier.heightIn(CustomTheme.elevation.spacing4dp))
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .height(CustomTheme.elevation.spacing48dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(CustomTheme.colors.inputBg)
                    .border(
                        width = 1.dp,
                        color = CustomTheme.colors.inputStroke,
                        shape = RoundedCornerShape(10.dp)
                    )
                    .then(
                        if (enabled) {
                            Modifier .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = ripple()
                            ) {
                                keyboardController?.hide()
                                showBottomSheet = true
                            }
                        } else Modifier
                    )

                    .weight(1f)
            ) {
                Spacer(Modifier.width(14.dp))
                selectedItem?.let {
                    Text(text = selectedItem, style = CustomTheme.typography.headlineRegular)
                } ?: run {
                    Text(
                        text = hint,
                        style = CustomTheme.typography.headlineRegular.copy(
                            color = CustomTheme.colors.placeholder
                        )
                    )
                }
                Spacer(Modifier.weight(1f))
                Icon(
                    painter = painterResource(R.drawable.ic_open_dropdown),
                    contentDescription = "ic_open_dropdown",
                    tint = CustomTheme.colors.description
                )
                Spacer(Modifier.width(14.dp))
            }
            if (closable) {
                IconButton(
                    onClick = { onItemSelect(null) },
                    modifier = Modifier.padding(start = CustomTheme.elevation.spacing16dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_close),
                        contentDescription = "ic_close",
                        tint = CustomTheme.colors.inputIcon,
                    )
                }
            }
        }
    }
}