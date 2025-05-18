package com.radlance.championshipfinal.common

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

interface DataStoreManager {

    suspend fun switchNotificationStatus(enabled: Boolean)

    fun notificationStatus(): Flow<Boolean>

    suspend fun saveToken(token: String)

    suspend fun removeToken()

    fun token(): Flow<String?>

    class Base @Inject constructor(
        private val dataStore: DataStore<Preferences>
    ) : DataStoreManager {
        override suspend fun switchNotificationStatus(enabled: Boolean) {
            dataStore.edit { settings ->
                settings[KEY_NOTIFICATIONS] = enabled
            }
        }

        override fun notificationStatus(): Flow<Boolean> {
            return dataStore.data.map { preferences -> preferences[KEY_NOTIFICATIONS] ?: false }
        }

        override suspend fun saveToken(token: String) {
            dataStore.edit { settings ->
                settings[KEY_TOKEN] = token
            }
        }

        override suspend fun removeToken() {
            dataStore.edit { settings ->
                settings.remove(KEY_TOKEN)
            }
        }

        override fun token(): Flow<String?> {
            return dataStore.data.map { preferences -> preferences[KEY_TOKEN] }
        }

        private companion object {
            val KEY_NOTIFICATIONS = booleanPreferencesKey("notifications")
            val KEY_TOKEN = stringPreferencesKey("token")
        }
    }
}