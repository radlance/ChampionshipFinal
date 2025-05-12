package com.radlance.championshipfinal.presentation.project

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.card.ProjectCard
import com.radlance.uikit.component.header.SmallHeader
import com.radlance.uikit.theme.CustomTheme

@Composable
fun ProjectScreen(
    navigateToProjectDetails: (Int) -> Unit,
    navigateToProjectCreation: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProjectViewModel = hiltViewModel()
) {
    val fetchProjectsUiState by viewModel.fetchProjectsUiState.collectAsState()

    BaseColumn(modifier = modifier) {
        Spacer(Modifier.height(28.dp))
        SmallHeader(
            title = stringResource(com.radlance.uikit.R.string.projects),
            endContent = {
                Icon(
                    painter = painterResource(com.radlance.uikit.R.drawable.ic_plus),
                    contentDescription = "ic_plus",
                    tint = CustomTheme.colors.inputIcon,
                    modifier = Modifier
                        .clip(CircleShape)
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = ripple(),
                            onClick = navigateToProjectCreation
                        )
                )
            }
        )
        Spacer(Modifier.height(36.dp))
        fetchProjectsUiState.Show(
            onSuccess = { projects ->
                if (projects.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = stringResource(R.string.no_projects),
                            style = CustomTheme.typography.title3SemiBold.copy(
                                color = CustomTheme.colors.placeholder
                            )
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.weight(1f),
                        contentPadding = PaddingValues(bottom = 99.dp),
                        verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp)
                    ) {
                        items(items = projects, key = { it.id }) { project ->
                            with(project) {
                                ProjectCard(
                                    title = name,
                                    startDate = startDate,
                                    endDate = endDate,
                                    onProjectClick = { navigateToProjectDetails(project.id) }
                                )
                            }
                        }
                    }
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
                        onClick = viewModel::fetchProjects,
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