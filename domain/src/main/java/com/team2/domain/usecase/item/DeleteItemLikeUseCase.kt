package com.team2.domain.usecase.item

import com.team2.domain.common.Resource
import com.team2.domain.repository.ItemRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DeleteItemLikeUseCase @Inject constructor(
    private val itemRepository: ItemRepository
) {
    suspend operator fun invoke(id: Int): Flow<Resource<Unit>> {
        return flow {
            emit(itemRepository.deleteItemLike(id))
        }
    }
}