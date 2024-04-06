package com.team2.data.repository

import com.team2.data.datasource.PreferenceManager
import com.team2.data.datasource.UserDataSource
import com.team2.data.model.UserRequest
import com.team2.domain.common.Resource
import com.team2.domain.entity.User
import com.team2.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val preferenceManager: PreferenceManager,

    ) : UserRepository {
    override suspend fun postUserSign(user: User): Flow<Resource<User>> {
        // TODO deviceId Util 만들어서 수정하기
        return userDataSource.postUserSign(
            UserRequest(nickname = user.nickname, deviceId = "12345678")
        )
            .map {
                when (it) {
                    is Resource.Success -> {
                        it.data?.let { signUserResponse ->
                            setUserToken(signUserResponse.token)
                            setUserNickname(signUserResponse.nickname)
                            Resource.Success(User(signUserResponse.nickname))
                        }?: Resource.Error(Exception())
                    }
                    is Resource.Error -> {
                        Resource.Error(it.exception)
                    }
                }
            }
    }

    override suspend fun postUserLogin(): Flow<Resource<Boolean>> {
        // token preference 에 저장
        return userDataSource.postUserLogin().map {
            when(it) {
                is Resource.Success -> {
                    it.data?.let {
                        Resource.Success(true)
                    }?: Resource.Error(Exception("error"))
                }
                is Resource.Error -> {
                    Resource.Error(it.exception)
                }
            }
        }
    }

    override suspend fun setUserToken(token: String) {
        preferenceManager.setUserToken(token)
    }

    override suspend fun hasUserToken(): Flow<Resource<Boolean>> {
        return flow {
            emit(Resource.Success(!preferenceManager.userToken.firstOrNull().isNullOrEmpty()))
        }
    }

    override suspend fun setUserNickname(nickname: String) {
        preferenceManager.setUserNickname(nickname)
    }

    override suspend fun getUserNickName(): Flow<Resource<String?>> {
        return flow {
            emit(Resource.Success(preferenceManager.userNickname.firstOrNull()))
        }
    }
}