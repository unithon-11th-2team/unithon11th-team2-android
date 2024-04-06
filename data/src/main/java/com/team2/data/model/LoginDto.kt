package com.team2.data.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val nickname: String,
    val deviceId: String
)

@Serializable
data class LoginUserResponse(
    val id: Int,
    val nickname: String
)

@Serializable
data class SignUserResponse(
    val id: Int,
    val nickname: String,
    val token: String
)