package com.team2.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BaseResponse<T> (
    val data: T
)