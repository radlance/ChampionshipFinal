package com.radlance.championshipfinal.presentation.navigation

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

interface Destination

@Keep
@Serializable
object Splash : Destination

@Keep
@Serializable
object SignIn : Destination

@Keep
@Serializable
data class PasswordCreation(
    val email: String,
    val password: String
) : Destination

@Keep
@Serializable
data class ProfileCreation(
    val email: String,
    val password: String
) : Destination

@Keep
@Serializable
object OtpEnter : Destination

@Keep
@Serializable
object ResetPassword : Destination

@Keep
@Serializable
object Main : Destination

@Keep
@Serializable
object Cart : Destination