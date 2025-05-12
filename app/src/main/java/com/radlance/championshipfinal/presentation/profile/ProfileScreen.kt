package com.radlance.championshipfinal.presentation.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val loadProfileUiState by viewModel.loadProfileUiState.collectAsState()


    BaseColumn(modifier = modifier) {
        Spacer(Modifier.height(CustomTheme.elevation.spacing32dp))
        loadProfileUiState.Show(
            onSuccess = { profile ->
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text(text = profile.toString())
                }
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