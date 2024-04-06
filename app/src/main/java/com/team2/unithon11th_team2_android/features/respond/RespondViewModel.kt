package com.team2.unithon11th_team2_android.features.respond

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.team2.domain.common.Resource
import com.team2.domain.entity.ItemType
import com.team2.domain.usecase.item.GetItemDetailUsecase
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.common.base.UiEffect
import com.team2.unithon11th_team2_android.common.base.UiEvent
import com.team2.unithon11th_team2_android.common.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class RespondViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getItemDetailUsecase: GetItemDetailUsecase
): BaseViewModel<RespondUiState, RespondUiEvent, RespondUiEffect>() {
    private val itemId: Int = checkNotNull(savedStateHandle["id"])

    init {
        setEvent(RespondUiEvent.FetchDetailData)
    }
    override fun createInitialState(): RespondUiState {
        return RespondUiState()
    }

    override fun handleEvent(event: RespondUiEvent) {
        when(event){
            is RespondUiEvent.FetchDetailData -> {
                fetchItemDetail()
            }
        }
    }

    private fun fetchItemDetail(){
        viewModelScope.launch {
            getItemDetailUsecase(itemId).collect {
                when(it){
                    is Resource.Success -> {
                        Timber.d("gowoon success detail")
                        setState(currentState.copy(
                            userName = it.data.userName,
                            type = it.data.type,
                            date = it.data.createdAt,
                            address = it.data.address,
                            message = it.data.message,
                            count = it.data.likeCount
                        ))
                    }
                    is Resource.Error -> {
                        Timber.d("gowoon fail detail ${it.exception}")
                    }
                }
            }
        }
    }
}

data class RespondUiState(
    val userName: String = "",
    val type: ItemType = ItemType.TYPE3,
    val date: String = "",
    val address: String = "",
    val message: String = "",
    val count: Int = 0,
    // TODO add comment
): UiState
sealed class RespondUiEvent: UiEvent{
    object FetchDetailData: RespondUiEvent()
}
sealed class RespondUiEffect: UiEffect{ }