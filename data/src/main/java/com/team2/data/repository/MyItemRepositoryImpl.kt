package com.team2.data.repository

import com.team2.data.datasource.ItemDataSource
import com.team2.data.model.response.toItem
import com.team2.data.model.response.toItemInfo
import com.team2.domain.common.Resource
import com.team2.domain.entity.ItemInfo
import com.team2.domain.repository.MyItemRepository
import javax.inject.Inject

class MyItemRepositoryImpl @Inject constructor(
    private val itemDataSource: ItemDataSource
) : MyItemRepository {
    override suspend fun getMyItems(): Resource<List<ItemInfo>> {
        return itemDataSource.getMyItemList().let {
            when (it) {
                is Resource.Success -> {
                    Resource.Success(it.data.map { item -> item.toItemInfo() })
                }

                is Resource.Error -> {
                    it
                }
            }
        }
    }

    override suspend fun deleteItem(itemId: Int): Resource<Boolean> {
        return itemDataSource.deleteMyItem(itemId).let {
            when(it) {
                is Resource.Success -> {
                    Resource.Success(true)
                }

                is Resource.Error -> {
                    it
                }
            }
        }
    }
}