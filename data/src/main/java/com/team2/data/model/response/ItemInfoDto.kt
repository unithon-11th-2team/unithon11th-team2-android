package com.team2.data.model.response

import com.team2.data.model.request.ItemDto
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemInfo
import com.team2.domain.entity.ItemType
import kotlinx.serialization.Serializable
@Serializable
data class ItemInfoDto(
    val id: Int,
    val message: String,
    val uid: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val type: String,
    val currentType: String,
    val createdAt: String,
    val modifiedAt: String
)

fun ItemInfoDto.toItemInfo() =
    ItemInfo(
        id = id,
        type = ItemType.valueOf(type),
        message = message,
        address = address,
        createdAt = createdAt
    )