package com.radlance.championshipfinal.presentation.project

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.radlance.championshipfinal.R
import com.radlance.championshipfinal.presentation.common.BaseColumn
import com.radlance.uikit.component.button.AppButton
import com.radlance.uikit.component.button.ButtonState
import com.radlance.uikit.component.header.SmallHeader
import com.radlance.uikit.component.input.EnterInputField
import com.radlance.uikit.component.select.AppSelector
import com.radlance.uikit.theme.CustomTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ProjectCreationScreen(
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ProjectViewModel = hiltViewModel()
) {

    val addProjectUiState by viewModel.addProjectUiState.collectAsState()
    val keyboardController = LocalSoftwareKeyboardController.current

    addProjectUiState.Show(
        onSuccess = {
            LaunchedEffect(Unit) {
                viewModel.fetchProjects()
                navigateUp()
                viewModel.resetState()
            }
        },
        onError = {},
        onLoading = {
            keyboardController?.hide()
        }
    )

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    var selectedType by rememberSaveable { mutableStateOf<String?>(null) }
    var projectNameFieldValue by rememberSaveable { mutableStateOf("") }
    var startDateFieldValue by rememberSaveable { mutableStateOf("") }
    var endDateFieldValue by rememberSaveable { mutableStateOf("") }
    var toWhomType by rememberSaveable { mutableStateOf<String?>(null) }
    var descriptionSourceFieldValue by rememberSaveable { mutableStateOf("") }
    var categoryType by rememberSaveable { mutableStateOf<String?>(null) }

    BaseColumn(modifier = modifier) {
        Spacer(Modifier.height(28.dp))
        SmallHeader(
            title = stringResource(com.radlance.uikit.R.string.projects)
        )
        Spacer(Modifier.height(36.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(CustomTheme.elevation.spacing16dp)
        ) {
            AppSelector(
                options = emptyList(),
                selectedItem = selectedType,
                onItemSelect = { selectedType = it },
                hint = stringResource(R.string.select_type),
                label = stringResource(R.string.type)
            )

            EnterInputField(
                value = projectNameFieldValue,
                onValueChange = { projectNameFieldValue = it },
                label = stringResource(R.string.project_name),
                hint = stringResource(R.string.enter_name),
                errorMessage = ""
            )

            EnterInputField(
                value = startDateFieldValue,
                onValueChange = { startDateFieldValue = it },
                label = stringResource(R.string.start_date),
                hint = stringResource(R.string.date_hint),
                errorMessage = ""
            )

            EnterInputField(
                value = endDateFieldValue,
                onValueChange = { endDateFieldValue = it },
                label = stringResource(R.string.end_date),
                hint = stringResource(R.string.date_hint),
                errorMessage = ""
            )

            AppSelector(
                options = emptyList(),
                selectedItem = toWhomType,
                onItemSelect = { toWhomType = it },
                hint = stringResource(R.string.select_to_whome),
                label = stringResource(R.string.to_whome)
            )

            EnterInputField(
                value = descriptionSourceFieldValue,
                onValueChange = { descriptionSourceFieldValue = it },
                label = stringResource(R.string.description_source),
                hint = stringResource(R.string.example_com),
                errorMessage = ""
            )

            AppSelector(
                options = emptyList(),
                selectedItem = categoryType,
                onItemSelect = { categoryType = it },
                hint = stringResource(R.string.select_category),
                label = stringResource(R.string.category)
            )

            AppButton(
                onClick = {
                    viewModel.addProject(
                        type = selectedType,
                        projectName = projectNameFieldValue,
                        startDate = LocalDate.parse(startDateFieldValue, dateFormatter),
                        endDate = if (endDateFieldValue.isBlank()) null else LocalDate.parse(
                            endDateFieldValue,
                            dateFormatter
                        ),
                        toWhom = toWhomType,
                        descriptionSource = descriptionSourceFieldValue,
                        category = categoryType
                    )
                },
                enabled = projectNameFieldValue.isNotBlank()
                        && runCatching {
                    LocalDate.parse(startDateFieldValue, dateFormatter)
                }.isSuccess
                        && descriptionSourceFieldValue.isNotBlank()
                        && endDateFieldValue.isBlank() || runCatching {
                    LocalDate.parse(endDateFieldValue, dateFormatter)
                }.isSuccess,
                label = stringResource(R.string.submit),
                buttonState = ButtonState.Big,
                modifier = Modifier.padding(bottom = 99.dp)
            )
        }
    }
}