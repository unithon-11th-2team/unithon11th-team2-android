package com.team2.data.model.request

import com.team2.data.model.response.ItemDto
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemType
import kotlinx.serialization.Serializable

@Serializable
data class ItemDto(
    val message: String,
    val latitude: Double,
    val longitude: Double,
    val type: String
)


fun Item.toItemDto() =
    ItemDto(message = message, latitude = latitude, longitude = longitude, type = type.name)