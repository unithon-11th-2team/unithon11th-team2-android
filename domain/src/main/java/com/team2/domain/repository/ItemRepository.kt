package com.team2.domain.repository

import com.team2.domain.common.Resource
import com.team2.domain.entity.Item

interface ItemRepository {
    suspend fun getItems(latitude: Double, longitude: Double): Resource<List<Item>>
    suspend fun postItem(item: Item): Resource<Unit>
}