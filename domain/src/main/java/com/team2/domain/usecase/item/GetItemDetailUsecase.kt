package com.team2.domain.usecase.item

import com.team2.domain.common.Resource
import com.team2.domain.entity.Item
import com.team2.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton
@Singleton
class GetItemDetailUsecase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(itemId: Int): Flow<Resource<Item>>{
        return flow { emit(itemRepository.getItemDetail(itemId)) }
    }
}