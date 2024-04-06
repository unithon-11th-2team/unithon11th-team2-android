package com.team2.data.di

import com.team2.data.datasource.PreferenceManager
import com.team2.data.network.ApiProvider
import com.team2.data.network.api.ItemService
import com.team2.data.network.api.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    @Provides
    fun provideUserService() : UserService {
        return ApiProvider.createWithoutRequestToken()
    }

    @Singleton
    @Provides
    fun provideItemService(preferenceManager: PreferenceManager): ItemService{
        return ApiProvider.create(preferenceManager)
    }
}