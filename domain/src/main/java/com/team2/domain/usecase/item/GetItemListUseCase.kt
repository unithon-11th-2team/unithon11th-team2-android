package com.team2.domain.usecase.item

import com.team2.domain.common.Resource
import com.team2.domain.entity.Item
import com.team2.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetItemListUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(latitude: Double, longitude: Double): Flow<Resource<List<Item>>> {
        return flow {
            emit(itemRepository.getItems(latitude, longitude))
        }
    }
}