package com.team2.unithon11th_team2_android.features.respond

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.team2.domain.common.Resource
import com.team2.domain.entity.ItemType
import com.team2.domain.usecase.item.DeleteItemLikeUseCase
import com.team2.domain.usecase.item.GetItemDetailUsecase
import com.team2.domain.usecase.item.RegisterItemLikeUseCase
import com.team2.domain.usecase.item.RegisterItemUseCase
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
    private val getItemDetailUsecase: GetItemDetailUsecase,
    private val registerItemLikeUseCase: RegisterItemLikeUseCase,
    private val deleteItemLikeUseCase: DeleteItemLikeUseCase
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

            is RespondUiEvent.OnClickItem ->{
                val diff = if(currentState.clicked){
                    deleteLike()
                    -1
                } else {
                    addLike()
                    +1
                }
                setState(
                    currentState.copy(
                        clicked = !currentState.clicked,
                        count = currentState.count.plus(diff)
                    )
                )
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

    private fun addLike(){
        viewModelScope.launch {
            registerItemLikeUseCase(itemId).collect{

            }
        }
    }

    private fun deleteLike(){
        viewModelScope.launch {
            deleteItemLikeUseCase(itemId).collect {

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
    val clicked: Boolean = false
    // TODO add comment
): UiState
sealed class RespondUiEvent: UiEvent{
    object FetchDetailData: RespondUiEvent()
    object OnClickItem: RespondUiEvent()
}
sealed class RespondUiEffect: UiEffect{ }