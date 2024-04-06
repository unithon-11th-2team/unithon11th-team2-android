package com.team2.data.network.api

import com.team2.data.model.BaseResponse
import com.team2.data.model.response.ItemDetailDto
import com.team2.data.model.response.ItemDto
import com.team2.data.model.response.ItemInfoDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
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

    @GET("api/v1/items/item-detail")
    suspend fun getItemDetail(
        @Query("itemId") itemId: Int
    ): Response<BaseResponse<ItemDetailDto>>

    @GET("api/v1/items/my-items")
    suspend fun getMyItems(
    ): Response<BaseResponse<List<ItemInfoDto>>>

    @DELETE("api/v1/items/{id}")
    suspend fun deleteMyItem(
        @Path("id") id: Int
    ): Response<BaseResponse<Unit>>

    @POST("/api/v1/item-like/{id}")
    suspend fun postItemLike(@Path("id") id: Int): Response<Unit>

    @DELETE("/api/v1/item-like/{id}")
    suspend fun deleteItemLike(@Path("id") id: Int): Response<Unit>
}