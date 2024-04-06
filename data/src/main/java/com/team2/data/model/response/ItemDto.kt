package com.team2.data.model.response

import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemType
import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val id: Int,
    val message: String,
    val uid: Int,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val type: String,
    val isMine: Boolean
)

fun ItemDto.toItem() =
    Item(
        message = message,
        type = ItemType.valueOf(type),
        latitude = latitude,
        longitude = longitude,
        isMine = isMine
    )