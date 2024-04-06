package com.team2.domain.repository

import com.team2.domain.common.Resource
import com.team2.domain.entity.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    suspend fun postUserSign(user: User) : Flow<Resource<User>>
    suspend fun postUserLogin() : Flow<Resource<Boolean>>

    suspend fun setUserToken(token: String)

    suspend fun hasUserToken() : Flow<Resource<Boolean>>

    suspend fun setUserNickname(nickname: String)

    suspend fun getUserNickName() : Flow<Resource<String?>>
}