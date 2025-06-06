package com.radlance.championshipfinal.data.di

import com.radlance.championshipfinal.data.auth.LocalAuthRepository
import com.radlance.championshipfinal.domain.auth.AuthRepository
import com.radlance.championshipfinal.presentation.auth.common.ValidationAuth
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    @Binds
    fun provideValidationAuth(validationAuth: ValidationAuth.Base): ValidationAuth

    @Binds
    fun provideAuthRepository(authRepository: LocalAuthRepository): AuthRepository
}