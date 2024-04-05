package com.team2.data.network.api

import com.team2.data.model.BaseResponse
import com.team2.data.model.LoginUserResponse
import com.team2.data.model.SignUserResponse
import com.team2.data.model.UserRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserService {
    @POST("/api/v1/users/login")
    suspend fun postUserLogin() : Response<BaseResponse<LoginUserResponse>>
    @POST("/api/v1/users/sign")
    suspend fun postUserSign(
        @Body user: UserRequest
    ) : Response<BaseResponse<SignUserResponse>>
}