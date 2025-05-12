package com.radlance.championshipfinal.data.profile

import com.radlance.championshipfinal.common.DataStoreManager
import com.radlance.championshipfinal.data.home.LocalStorage
import com.radlance.championshipfinal.domain.profile.ProfileRepository
import com.radlance.championshipfinal.domain.profile.User
import com.radlance.championshipfinal.domain.remote.FetchResult
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalProfileRepository @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : ProfileRepository {
    override fun loadProfile(): FetchResult<User> {
        return try {
            LocalStorage.currentUser?.let {
                FetchResult.Success(it)
            } ?: FetchResult.Error(null)
        } catch (e: Exception) {
            FetchResult.Error(null)
        }
    }

    override suspend fun switchNotificationStatus(enabled: Boolean) {
        dataStoreManager.switchNotificationStatus(enabled)
    }

    override fun notificationStatus(): Flow<Boolean> = dataStoreManager.notificationStatus()
}