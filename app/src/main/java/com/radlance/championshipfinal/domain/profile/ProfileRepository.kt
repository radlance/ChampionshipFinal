package com.radlance.championshipfinal.domain.profile

import com.radlance.championshipfinal.domain.remote.FetchResult

interface ProfileRepository {
    fun loadProfile(): FetchResult<User>
}