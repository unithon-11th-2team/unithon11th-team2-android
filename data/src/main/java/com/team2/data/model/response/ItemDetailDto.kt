package com.team2.data.model.response

import com.team2.data.model.request.ItemDto
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemType
import kotlinx.serialization.Serializable

@Serializable
data class ItemDetailDto(
    val itemId: Int,
    val uid: Int,
    val nickname: String,
    val type: String,
    val currentType: String,
    val message: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val commentList: List<Comment>,
    val createdAt: String,
    val likeCounts: Int,
)

@Serializable
data class Comment(
    val uid: Int,
    val nickname: String,
    val message: String,
    val likeCount: Int
)

fun ItemDetailDto.toItem() =
    Item(
        id = itemId,
        message = message,
        type = ItemType.valueOf(currentType),
        latitude = latitude,
        longitude = longitude,
        address = address,
        createdAt = createdAt.substring(0, 10),
        likeCount = likeCounts,
        userName = nickname
    )
