package com.team2.domain.repository

import com.team2.domain.entity.User

interface UserRepository {
    suspend fun postUserSign(user: User)
    suspend fun postUserLogin()
}