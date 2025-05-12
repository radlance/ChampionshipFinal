package com.radlance.championshipfinal.data.di

import com.radlance.championshipfinal.data.profile.LocalProfileRepository
import com.radlance.championshipfinal.domain.profile.ProfileRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProfileModule {

    @Binds
    fun provideProfileRepository(profileRepository: LocalProfileRepository): ProfileRepository
}