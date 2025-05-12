package com.radlance.championshipfinal.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.radlance.championshipfinal.common.DataStoreManager
import com.radlance.championshipfinal.common.ProvideResources
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CommonModule {

    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context): Context = context

    @Provides
    @Singleton
    fun provideProvideResources(context: Context): ProvideResources {
        return ProvideResources.Base(context)
    }

    @Provides
    @Singleton
    fun provideDataStorePreferences(context: Context): DataStore<Preferences> = context.dataStore

    @Provides
    @Singleton
    fun provideDataStoreManager(dataStore: DataStore<Preferences>): DataStoreManager {
        return DataStoreManager.Base(dataStore)
    }

    companion object {
        private val Context.dataStore by preferencesDataStore("settings")
    }
}