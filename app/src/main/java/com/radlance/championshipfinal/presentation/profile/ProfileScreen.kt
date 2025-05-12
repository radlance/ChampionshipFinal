package com.radlance.championshipfinal.presentation.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.AppSwitch
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ProfileScreen(
    navigateToSignIn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val notificationEnabledStatus by viewModel.notificationsEnabled.collectAsState()
    val loadProfileUiState by viewModel.loadProfileUiState.collectAsState()

    BaseColumn(modifier = modifier) {
        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))
        loadProfileUiState.Show(
            onSuccess = { profile ->
                Text(text = profile.firstName, style = CustomTheme.typography.title1Heavy)
                Spacer(Modifier.height(CustomTheme.elevation.spacing8dp))
                Text(
                    text = "+7 967 078-58-37",
                    style = CustomTheme.typography.headlineRegular.copy(
                        color = CustomTheme.colors.placeholder
                    )
                ) // TODO get a phone number from somewhere
                Spacer(Modifier.height(23.dp))
                Row(
                    modifier = Modifier.height(CustomTheme.elevation.spacing64dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_orders),
                        contentDescription = "ic_orders",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(Modifier.width(CustomTheme.elevation.spacing20dp))
                    Text(
                        text = stringResource(R.string.my_orders),
                        style = CustomTheme.typography.title3SemiBold
                    )
                }

                Row(
                    modifier = Modifier.height(CustomTheme.elevation.spacing64dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_settings),
                        contentDescription = "ic_settings",
                        modifier = Modifier.size(32.dp)
                    )
                    Spacer(Modifier.width(CustomTheme.elevation.spacing20dp))
                    Text(
                        text = stringResource(R.string.notifications),
                        style = CustomTheme.typography.title3SemiBold,
                        modifier = Modifier.weight(1f)
                    )
                    AppSwitch(
                        checked = notificationEnabledStatus,
                        onCheckedChange = { viewModel.switchNotificationState(it) }
                    )
                }
                Spacer(Modifier.weight(1f))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing24dp)
                ) {
                    Text(
                        text = stringResource(R.string.privacy_policy),
                        style = CustomTheme.typography.textMedium.copy(
                            color = CustomTheme.colors.placeholder
                        )
                    )

                    Text(
                        text = stringResource(R.string.user_agreement),
                        style = CustomTheme.typography.textMedium.copy(
                            color = CustomTheme.colors.placeholder
                        )
                    )

                    Text(
                        text = stringResource(R.string.exit),
                        style = CustomTheme.typography.textMedium.copy(
                            color = CustomTheme.colors.error
                        ),
                        modifier = Modifier.clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = navigateToSignIn
                        )
                    )
                }
                Spacer(Modifier.weight(1f))
            },
            onError = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = stringResource(R.string.loading_error),
                        style = CustomTheme.typography.title3SemiBold.copy(
                            color = CustomTheme.colors.placeholder
                        ),
                        modifier = Modifier.padding(vertical = CustomTheme.elevation.spacing8dp)
                    )
                    AppButton(
                        onClick = viewModel::loadProfile,
                        label = stringResource(R.string.retry),
                        buttonState = ButtonState.Chips
                    )
                }
            },
            onLoading = {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    CircularProgressIndicator()
                }
            }
        )
    }
}