package com.team2.data.repository

import com.team2.data.datasource.ItemDataSource
import com.team2.data.model.request.toItemDto
import com.team2.data.model.response.toItem
import com.team2.domain.common.Resource
import com.team2.domain.entity.Item
import com.team2.domain.repository.ItemRepository
import javax.inject.Inject

class ItemRepositoryImpl @Inject constructor(
    private val itemDataSource: ItemDataSource
) : ItemRepository {
    override suspend fun getItems(latitude: Double, longitude: Double): Resource<List<Item>> {
        return itemDataSource.getItems(latitude, longitude).let {
            when (it) {
                is Resource.Success -> {
                    Resource.Success(it.data.map { item -> item.toItem() })
                }

                is Resource.Error -> {
                    it
                }
            }
        }
    }

    override suspend fun postItem(item: Item): Resource<Unit> {
        return itemDataSource.postItem(item.toItemDto())
    }

    override suspend fun getItemDetail(itemId: Int): Resource<Item> {
        return itemDataSource.getItemDetail(itemId).let {
            when(it){
                is Resource.Success -> { Resource.Success(it.data.toItem()) }
                is Resource.Error -> { it }
            }
        }
    }

    override suspend fun postItemLike(id: Int): Resource<Unit> {
        return itemDataSource.postItemLike(id)
    }

    override suspend fun deleteItemLike(id: Int): Resource<Unit> {
        return itemDataSource.deleteItemLike(id)
    }
}