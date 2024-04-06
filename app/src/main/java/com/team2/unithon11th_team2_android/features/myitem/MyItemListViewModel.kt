package com.team2.unithon11th_team2_android.features.myitem

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.team2.domain.common.Resource
import com.team2.domain.entity.ItemInfo
import com.team2.domain.usecase.my_item.DeleteMyItemListUseCase
import com.team2.domain.usecase.my_item.GetMyItemListUseCase
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.common.base.UiEffect
import com.team2.unithon11th_team2_android.common.base.UiEvent
import com.team2.unithon11th_team2_android.common.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class MyItemListViewModel @Inject constructor(
    private val deleteMyItemListUseCase: DeleteMyItemListUseCase,
    private val getMyItemListUseCase: GetMyItemListUseCase
) : BaseViewModel<MyItemListState, MyItemListUiEvent, UiEffect>() {
    override fun createInitialState(): MyItemListState {
        return MyItemListState(listOf())
    }

    override fun handleEvent(event: MyItemListUiEvent) {
        when (event) {
            is MyItemListUiEvent.GetMyItemList -> {
                getMyItemList()
            }
            is MyItemListUiEvent.DeleteItem -> {
                deleteMyItem(item = event.item)
            }

            is MyItemListUiEvent.OnClickedItem -> {

            }

            else -> {

            }
        }
    }

    private fun getMyItemList() {
        viewModelScope.launch {
            getMyItemListUseCase(
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        setState(currentState.copy(items = it.data))
                        Timber.e( "success fetch list ${it.data.size}")
                    }

                    is Resource.Error -> {
                        // TODO Error
                        Timber.e("error myItemList viewmodel getMyItemList ${it.exception}")
                    }
                }
            }
        }
    }

    private fun deleteMyItem(item: ItemInfo) {
        viewModelScope.launch {
            deleteMyItemListUseCase(item.id).collect {
                when (it) {
                    is Resource.Success -> {
                        getMyItemList()
                    }

                    is Resource.Error -> {
                        // TODO Error
                        getMyItemList()
                        Timber.e("error myItemList viewmodel deleteMyItem ${it.exception}")
                    }
                }
            }
        }
    }

}

data class MyItemListState(
    val items: List<ItemInfo>
) : UiState

sealed class MyItemListUiEvent : UiEvent {
    data class DeleteItem(val item: ItemInfo) : MyItemListUiEvent()
    object GetMyItemList : MyItemListUiEvent()
    data class OnClickedItem(val item: ItemInfo) : MyItemListUiEvent()
}