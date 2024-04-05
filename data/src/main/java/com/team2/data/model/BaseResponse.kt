package com.team2.data.model

sealed class BaseResponse<out T> (
    val data: T
)