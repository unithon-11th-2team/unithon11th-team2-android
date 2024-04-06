package com.team2.data.di

import com.team2.data.datasource.ItemDataSource
import com.team2.data.network.api.ItemService
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
    fun provideItemDataSource(itemApi: ItemService): ItemDataSource {
        return ItemDataSource(itemApi)
    }
}