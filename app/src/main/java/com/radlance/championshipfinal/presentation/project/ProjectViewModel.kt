package com.radlance.championshipfinal.presentation.project

import com.radlance.championshipfinal.domain.project.Project
import com.radlance.championshipfinal.domain.project.ProjectRepository
import com.radlance.championshipfinal.presentation.common.BaseViewModel
import com.radlance.championshipfinal.presentation.common.FetchResultMapper
import com.radlance.championshipfinal.presentation.common.FetchResultUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
    private val projectRepository: ProjectRepository
) : BaseViewModel() {
    private val _fetchProjectsUiState =
        MutableStateFlow<FetchResultUiState<List<Project>>>(FetchResultUiState.Initial())
    val fetchProjectsUiState: StateFlow<FetchResultUiState<List<Project>>> =
        _fetchProjectsUiState.onStart {
            fetchProjects()
        }.stateInViewModel(initialValue = FetchResultUiState.Initial())

    private val _addProjectUiState =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val addProjectUiState: StateFlow<FetchResultUiState<Unit>>
        get() = _addProjectUiState.asStateFlow()

    private val _updateProjectUiState =
        MutableStateFlow<FetchResultUiState<Unit>>(FetchResultUiState.Initial())
    val updateProjectUiState: StateFlow<FetchResultUiState<Unit>>
        get() = _updateProjectUiState.asStateFlow()

    fun fetchProjects(showLoading: Boolean = true) {
        if (showLoading) {
            _fetchProjectsUiState.value = FetchResultUiState.Loading()
        }

        handle(
            action = {
                projectRepository.fetchProjects()
            }
        ) {
            _fetchProjectsUiState.value = it.map(FetchResultMapper())
        }
    }

    fun addProject(
        type: String?,
        projectName: String,
        startDate: LocalDate,
        endDate: LocalDate?,
        toWhom: String?,
        descriptionSource: String,
        category: String?
    ) {
        val project = Project(
            type = type,
            name = projectName,
            startDate = startDate,
            endDate = endDate,
            toWhom = toWhom,
            descriptionSource = descriptionSource,
            category = category
        )

        _addProjectUiState.value = FetchResultUiState.Loading()
        handle(
            action = {
                projectRepository.addProject(project)
            }
        ) {
            _addProjectUiState.value = it.map(FetchResultMapper())
        }
    }

    fun updateProject(
        id: Int,
        type: String?,
        projectName: String,
        startDate: LocalDate,
        endDate: LocalDate?,
        toWhom: String?,
        descriptionSource: String,
        category: String?
    ) {
        val project = Project(
            id = id,
            type = type,
            name = projectName,
            startDate = startDate,
            endDate = endDate,
            toWhom = toWhom,
            descriptionSource = descriptionSource,
            category = category
        )

        _updateProjectUiState.value = FetchResultUiState.Loading()
        handle(
            action = {
                projectRepository.updateProject(project)
            }
        ) {
            _updateProjectUiState.value = it.map(FetchResultMapper())
        }
    }

    fun resetState() {
        _addProjectUiState.value = FetchResultUiState.Initial()
        _updateProjectUiState.value = FetchResultUiState.Initial()
    }
}