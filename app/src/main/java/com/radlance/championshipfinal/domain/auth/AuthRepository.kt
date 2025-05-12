package com.radlance.championshipfinal.domain.auth

import com.radlance.championshipfinal.domain.profile.User
import com.radlance.championshipfinal.domain.remote.FetchResult

interface AuthRepository {

    fun signIn(user: User): FetchResult<Boolean>

    fun signUp(user: User): FetchResult<Unit>

}