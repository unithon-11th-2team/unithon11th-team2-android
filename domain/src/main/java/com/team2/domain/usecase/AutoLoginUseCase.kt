package com.team2.domain.usecase

import com.team2.domain.common.Resource
import com.team2.domain.entity.User
import com.team2.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AutoLoginUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(): Flow<Resource<Boolean>> {
        return userRepository.postUserLogin()
    }

}