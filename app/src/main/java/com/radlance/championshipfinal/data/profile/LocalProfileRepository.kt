package com.radlance.championshipfinal.data.profile

import com.radlance.championshipfinal.data.home.LocalStorage
import com.radlance.championshipfinal.domain.profile.ProfileRepository
import com.radlance.championshipfinal.domain.profile.User
import com.radlance.championshipfinal.domain.remote.FetchResult
import javax.inject.Inject

class LocalProfileRepository @Inject constructor() : ProfileRepository {
    override fun loadProfile(): FetchResult<User> {
        return try {
            LocalStorage.currentUser?.let {
                FetchResult.Success(it)
            } ?: FetchResult.Error(null)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }
}