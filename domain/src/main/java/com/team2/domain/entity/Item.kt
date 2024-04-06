package com.team2.domain.entity

data class Item(
    val message: String,
    val type: ItemType,
    val latitude: Double,
    val longitude: Double,
    val isMine: Boolean
)