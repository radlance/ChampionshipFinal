package com.radlance.api

import kotlinx.serialization.Serializable

@Serializable
data class UserRegistrationDto(
    val email: String,
    val password: String,
    val name: String
)
