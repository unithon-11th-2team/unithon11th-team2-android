package com.team2.data.repository

import com.team2.data.datasource.UserDataSource
import com.team2.data.model.UserRequest
import com.team2.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {
    override suspend fun postUserSign(user: com.team2.domain.entity.User) {
        // TODO deviceId Util 만들어서 수정하기
        userDataSource.postUserSign(UserRequest(nickname = user.nickname, deviceId = "123"))
    }

    override suspend fun postUserLogin() {
        // token preference 에 저장
        userDataSource.postUserLogin()
    }
}