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
object PasswordCreation : Destination

@Keep
@Serializable
object ProfileCreation : Destination