package com.team2.data.di

import com.team2.data.repository.ItemRepositoryImpl
import com.team2.data.repository.MyItemRepositoryImpl
import com.team2.data.repository.UserRepositoryImpl
import com.team2.domain.repository.ItemRepository
import com.team2.domain.repository.MyItemRepository
import com.team2.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl
    ): UserRepository

    @Binds
    @Singleton
    abstract fun bindItemRepository(
        itemRepositoryImpl: ItemRepositoryImpl
    ): ItemRepository

    @Binds
    @Singleton
    abstract fun bindMyItemRepository(
        itemRepositoryImpl: MyItemRepositoryImpl
    ): MyItemRepository
}