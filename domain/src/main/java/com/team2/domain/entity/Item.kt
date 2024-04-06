package com.team2.domain.entity

data class Item(
    val id: Int? = null,
    val message: String,
    val type: ItemType,
    val latitude: Double,
    val longitude: Double,
    val address: String = "",
    val isMine: Boolean = true,
    val createdAt: String = "",
    val likeCount: Int = 0,
    val userName: String = ""
)