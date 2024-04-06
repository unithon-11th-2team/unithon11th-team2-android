package com.team2.domain.entity

data class ItemInfo(
    val id: Int,
    val message: String,
    val type: ItemType,
    val address: String,
    val createdAt: String
)