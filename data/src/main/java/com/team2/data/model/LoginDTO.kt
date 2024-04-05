package com.team2.data.model

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class UserRequest(
    val nickname: String,
    val deviceId: String
)

@Keep
@Serializable
data class LoginUserResponse(
    val id: Int,
    val nickname: String
)

@Keep
@Serializable
data class SignUserResponse(
    val id: Int,
    val nickname: String,
    val token: String
)