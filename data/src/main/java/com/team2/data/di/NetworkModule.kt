package com.team2.data.di

import com.team2.data.network.ApiProvider
import com.team2.data.network.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Singleton
    @Provides
    fun provideUserService() : UserService {
        return ApiProvider.retrofit.create(UserService::class.java)
    }
}