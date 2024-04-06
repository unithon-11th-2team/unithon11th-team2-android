package com.team2.domain.usecase

import com.team2.domain.common.Resource
import com.team2.domain.entity.User
import com.team2.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginUseCase @Inject constructor(
    private val userRepository: UserRepository
){
    suspend operator fun invoke(nickname: String): Flow<Resource<User>> {
        return userRepository.postUserSign(User(nickname = nickname)).onEach {
            if (it is Resource.Success) {
                it.data?.let { user ->

                }
            }
        }
    }

}