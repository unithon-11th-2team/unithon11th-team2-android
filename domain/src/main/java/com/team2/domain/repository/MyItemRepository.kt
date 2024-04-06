package com.team2.domain.repository

import com.team2.domain.common.Resource
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemInfo

interface MyItemRepository {
    suspend fun getMyItems(): Resource<List<ItemInfo>>
    suspend fun deleteItem(itemId: Int): Resource<Boolean>
}