package com.radlance.championshipfinal.presentation.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeGesturesPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.radlance.championshipfinal.R
import com.radlance.uikit.theme.CustomTheme

@Composable
fun PasswordCreationScreen(
    modifier: Modifier = Modifier,
    viewModel: EnterPasswordViewModel = viewModel()
) {
    val fillProgressList = viewModel.passwordProgressList()
    Column(
        modifier = modifier
            .safeGesturesPadding()
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp)
    ) {
        Spacer(Modifier.height(CustomTheme.elevation.spacing40dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
            Text(
                text = stringResource(R.string.skip),
                style = CustomTheme.typography.textRegular.copy(color = CustomTheme.colors.accent)
            )
        }
        Spacer(Modifier.height(CustomTheme.elevation.spacing40dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.create_password),
                style = CustomTheme.typography.title1Heavy
            )
            Spacer(Modifier.height(CustomTheme.elevation.spacing16dp))
            Text(
                text = stringResource(R.string.for_protection_your_data),
                style = CustomTheme.typography.textRegular.copy(
                    color = CustomTheme.colors.placeholder
                )
            )
            Spacer(Modifier.height(CustomTheme.elevation.spacing56dp))
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                repeat(4) { index ->
                    PasswordCircle(filled = fillProgressList.getOrNull(index) != null)
                }
            }
            Spacer(Modifier.height(60.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {

                val enterNumber: (Int) -> Unit = { viewModel.enterNumber(it) }


                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    PasswordEnterButton(number = 1, onClick = enterNumber)
                    PasswordEnterButton(number = 2, onClick = enterNumber)
                    PasswordEnterButton(number = 3, onClick = enterNumber)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    PasswordEnterButton(number = 4, onClick = enterNumber)
                    PasswordEnterButton(number = 5, onClick = enterNumber)
                    PasswordEnterButton(number = 6, onClick = enterNumber)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    PasswordEnterButton(number = 7, onClick = enterNumber)
                    PasswordEnterButton(number = 8, onClick = enterNumber)
                    PasswordEnterButton(number = 9, onClick = enterNumber)
                }

                Row(horizontalArrangement = Arrangement.spacedBy(24.dp)) {
                    Box(Modifier.size(80.dp))
                    PasswordEnterButton(number = 0, onClick = enterNumber)
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null
                            ) { viewModel.removeNumber() }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_delete),
                            contentDescription = "ic_delete"
                        )
                    }
                }
            }
        }
        Spacer(Modifier.height(80.dp))
    }
}