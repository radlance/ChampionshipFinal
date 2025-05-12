package com.radlance.championshipfinal.domain.profile

import com.radlance.championshipfinal.domain.remote.FetchResult
import kotlinx.coroutines.flow.Flow

interface ProfileRepository {

    fun loadProfile(): FetchResult<User>

    suspend fun switchNotificationStatus(enabled: Boolean)

    fun notificationStatus(): Flow<Boolean>
}