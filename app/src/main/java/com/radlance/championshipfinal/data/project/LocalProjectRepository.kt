package com.radlance.championshipfinal.data.project

import com.radlance.championshipfinal.data.home.LocalStorage
import com.radlance.championshipfinal.domain.project.Project
import com.radlance.championshipfinal.domain.project.ProjectRepository
import com.radlance.championshipfinal.domain.remote.FetchResult
import javax.inject.Inject

class LocalProjectRepository @Inject constructor() : ProjectRepository {
    override fun fetchProjects(): FetchResult<List<Project>> {
        return try {
            FetchResult.Success(LocalStorage.projects)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override fun addProject(project: Project): FetchResult<Unit> {
        return try {
            LocalStorage.projects.add(project.copy(id = LocalStorage.projects.size + 1))
            FetchResult.Success(Unit)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override fun updateProject(project: Project): FetchResult<Unit> {
        return try {
            LocalStorage.projects = LocalStorage.projects.map {
                if (it.id == project.id) {
                    project
                } else it
            }.toMutableList()

            FetchResult.Success(Unit)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }
}