package com.radlance.championshipfinal.domain.profile

data class User(
    val email: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val patronymic: String = "",
    val dateOfBirth: String = "",
    val gender: String = "",
    val telegram: String = ""
)
