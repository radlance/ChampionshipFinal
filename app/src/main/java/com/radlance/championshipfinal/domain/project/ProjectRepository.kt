package com.radlance.championshipfinal.domain.project

import com.radlance.championshipfinal.domain.remote.FetchResult

interface ProjectRepository {

    fun fetchProjects(): FetchResult<List<Project>>

    fun addProject(project: Project): FetchResult<Unit>

    fun updateProject(project: Project): FetchResult<Unit>
}