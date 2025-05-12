package com.radlance.championshipfinal.data.auth

import com.radlance.championshipfinal.data.home.LocalStorage
import com.radlance.championshipfinal.domain.auth.AuthRepository
import com.radlance.championshipfinal.domain.profile.User
import com.radlance.championshipfinal.domain.remote.FetchResult
import javax.inject.Inject

class LocalAuthRepository @Inject constructor() : AuthRepository {
    override fun signIn(user: User): FetchResult<Boolean> {
        return try {
            val existingUser = LocalStorage.profiles.find { it.email == user.email }
            existingUser?.let {
                if (user.password == it.password) {
                    LocalStorage.currentUser = it
                    FetchResult.Success(false)
                } else {
                    FetchResult.Error(false)
                }
            } ?: FetchResult.Error(true)
        } catch (e: Exception) {
            FetchResult.Error(false)
        }
    }

    override fun signUp(user: User): FetchResult<Unit> {
        return try {
            LocalStorage.profiles.add(user)
            LocalStorage.currentUser = LocalStorage.profiles.last()
            FetchResult.Success(Unit)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }
}