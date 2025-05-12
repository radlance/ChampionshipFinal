package com.radlance.championshipfinal.domain.project

import java.time.LocalDate

data class Project(
    val type: String?,
    val name: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val toWhom: String?,
    val descriptionSource: String,
    val category: String?,
    val id: Int = -1
)