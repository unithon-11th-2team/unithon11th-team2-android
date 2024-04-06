package com.team2.data.model

import kotlinx.serialization.Serializable

@Serializable
open class BaseResponse<out T> (
    val data: T
)