package com.team2.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    @SerialName("nickname")
    val nickname: String,
    @SerialName("deviceId")
    val deviceId: String
)

@Serializable
data class LoginUserResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("nickname")
    val nickname: String
)

@Serializable
data class SignUserResponse(
    @SerialName("id")
    val id: Int,
    @SerialName("nickname")
    val nickname: String,
    @SerialName("token")
    val token: String
)