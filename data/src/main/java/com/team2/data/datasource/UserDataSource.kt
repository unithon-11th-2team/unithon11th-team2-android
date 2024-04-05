package com.team2.data.datasource

import com.team2.data.model.LoginUserResponse
import com.team2.data.model.SignUserResponse
import com.team2.data.model.UserRequest
import com.team2.data.network.api.UserService
import com.team2.domain.common.Resource
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserService
){
    suspend fun postUserSign(user: UserRequest) : Resource<SignUserResponse?> {
        return try {
            Resource.Success(userApi.postUserSign(user).body()?.data)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    suspend fun postUserLogin() : Resource<LoginUserResponse?> {
        return try {
            Resource.Success(userApi.postUserLogin().body()?.data)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}