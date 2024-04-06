package com.team2.data.di

import com.team2.data.datasource.UserDataSource
import com.team2.data.network.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Singleton
    @Provides
    fun provideUserDataSource(
        userApi: UserService
    ): UserDataSource {
        return UserDataSource(userApi)
    }
}