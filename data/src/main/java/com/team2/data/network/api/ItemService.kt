package com.team2.data.network.api

import com.team2.data.model.BaseResponse
import com.team2.data.model.response.ItemDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ItemService {
    @GET("api/v1/items")
    suspend fun getItems(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double
    ): Response<BaseResponse<List<ItemDto>>>

    @POST("api/v1/items")
    suspend fun postItem(
        @Body data: com.team2.data.model.request.ItemDto
    ): Response<BaseResponse<ItemDto>>
}