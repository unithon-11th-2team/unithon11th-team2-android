package com.team2.domain.usecase.my_item

import com.team2.domain.common.Resource
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemInfo
import com.team2.domain.repository.ItemRepository
import com.team2.domain.repository.MyItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMyItemListUseCase @Inject constructor(
    private val itemRepository: MyItemRepository
) {
    suspend operator fun invoke(): Flow<Resource<List<ItemInfo>>> {
        return flow {
            emit(itemRepository.getMyItems())
        }
    }
}