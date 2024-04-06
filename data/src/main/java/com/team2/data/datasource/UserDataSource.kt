package com.team2.data.datasource

import com.team2.data.model.LoginUserResponse
import com.team2.data.model.SignUserResponse
import com.team2.data.model.UserRequest
import com.team2.data.network.api.UserService
import com.team2.domain.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val userApi: UserService
) {
    suspend fun postUserSign(user: UserRequest): Flow<Resource<SignUserResponse?>> {
        return flow {
            emit(
                userApi.postUserSign(
                    UserRequest(
                        nickname = user.nickname, deviceId = "123"
                    )
                )
            )
        }.map {
            if (it.isSuccessful) {
                it.body()?.let { body ->
                    Resource.Success(body.data)
                } ?: Resource.Error(Exception())
            } else {
                Timber.d(it.message().toString())
                Resource.Error(Exception(it.message()))
            }
        }
    }

    suspend fun postUserLogin(): Flow<Resource<LoginUserResponse?>> {
        return flow { emit(userApi.postUserLogin()) }.map {
            if (it.isSuccessful) {
                it.body()?.let { body ->
                    Resource.Success(body.data)
                } ?: Resource.Error(Exception())
            } else {
                Timber.d(it.message().toString())
                Resource.Error(Exception(it.message()))
            }
        }
    }
}