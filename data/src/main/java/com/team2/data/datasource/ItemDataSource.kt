package com.team2.data.datasource

import com.team2.data.model.response.ItemDetailDto
import com.team2.data.model.response.ItemDto
import com.team2.data.model.response.ItemInfoDto
import com.team2.data.network.api.ItemService
import com.team2.domain.common.Resource
import javax.inject.Inject

class ItemDataSource @Inject constructor(
    private val itemApi: ItemService
) {
    suspend fun getItems(
        latitude: Double,
        longitude: Double
    ): Resource<List<ItemDto>> = try {
        (itemApi.getItems(latitude, longitude)).let {
            if(it.isSuccessful){
                it.body()?.let { result ->
                    Resource.Success(result.data)
                } ?: Resource.Error(Exception("null"))
            }else{
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }

    suspend fun postItem(item: com.team2.data.model.request.ItemDto): Resource<Unit> = try {
        itemApi.postItem(item).let {
            if(it.isSuccessful){
                Resource.Success(Unit)
            } else {
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception){
        Resource.Error(e)
    }

    suspend fun getItemDetail(itemId: Int): Resource<ItemDetailDto> = try {
        itemApi.getItemDetail(itemId).let {
            if(it.isSuccessful){
                it.body()?.let { Resource.Success(it.data) } ?: Resource.Error(Exception("null"))
            } else {
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception){
        Resource.Error(e)
    }

    suspend fun getMyItemList() : Resource<List<ItemInfoDto>> = try {
        (itemApi.getMyItems()).let {
            if(it.isSuccessful){
                it.body()?.let { result ->
                    Resource.Success(result.data)
                } ?: Resource.Error(Exception("null"))
            }else{
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }

    suspend fun deleteMyItem(itemId: Int) : Resource<Unit> = try {
        (itemApi.deleteMyItem(itemId)).let {
            if(it.isSuccessful){
                it.body()?.let { result ->
                    Resource.Success(result.data)
                } ?: Resource.Error(Exception("null"))
            }else{
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }


    suspend fun postItemLike(id: Int): Resource<Unit> = try {
        itemApi.postItemLike(id).let {
            if(it.isSuccessful){
                Resource.Success(Unit)
            } else {
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }

    suspend fun deleteItemLike(id: Int): Resource<Unit> = try {
        itemApi.deleteItemLike(id).let {
            if(it.isSuccessful){
                Resource.Success(Unit)
            } else {
                Resource.Error(Exception(it.message()))
            }
        }
    } catch (e: Exception) {
        Resource.Error(e)
    }
}