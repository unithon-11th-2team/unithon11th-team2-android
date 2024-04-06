package com.team2.unithon11th_team2_android.features.map

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.team2.domain.common.Resource
import com.team2.domain.entity.Item
import com.team2.domain.entity.ItemType
import com.team2.domain.usecase.item.GetItemListUseCase
import com.team2.domain.usecase.item.RegisterItemUseCase
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.common.base.UiEffect
import com.team2.unithon11th_team2_android.common.base.UiEvent
import com.team2.unithon11th_team2_android.common.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
internal class MapViewModel @Inject constructor(
    private val getItemListUseCase: GetItemListUseCase,
    private val registerItemUseCase: RegisterItemUseCase
) : BaseViewModel<MapUiState, MapUiEvent, MapUiEffect>() {
    override fun createInitialState(): MapUiState {
        return MapUiState()
    }

    override fun handleEvent(event: MapUiEvent) {
        when (event) {
            is MapUiEvent.InitCurrentLocation -> {
                setState(
                    currentState.copy(
                        currentLocation = LatLng(
                            event.latitude,
                            event.longitude
                        )
                    )
                )
            }

            is MapUiEvent.FetchItemList -> {
                fetchItemList()
            }

            is MapUiEvent.OnClickDropItem -> {
                setState(currentState.copy(sheetData = currentState.sheetData.copy(step = Step.LEVEL)))
            }

            is MapUiEvent.OnSelectLevel -> {
                setState(currentState.copy(sheetData = currentState.sheetData.copy(step = Step.CONTENT, level = event.type)))
            }

            is MapUiEvent.OnClickRegister -> {
                registerItem()
            }
        }
    }

    private fun fetchItemList() {
        viewModelScope.launch {
            getItemListUseCase(
                currentState.currentLocation.latitude,
                currentState.currentLocation.longitude
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        setState(currentState.copy(items = it.data))
                        Log.e("gowoon", "success fetch list ${it.data.size}")
                    }

                    is Resource.Error -> {
                        // TODO Error
                        Log.e("gowoon", "error map viewmodel fetchItemList ${it.exception}")
                    }
                }
            }
        }
    }

    private fun registerItem(){
        viewModelScope.launch {
            registerItemUseCase(
                Item(
                    message = currentState.sheetData.text,
                    type = currentState.sheetData.level ?: ItemType.TYPE5,
                    latitude = currentState.currentLocation.latitude,
                    longitude = currentState.currentLocation.longitude,
                    isMine = true
                )
            ).collect {
                when (it) {
                    is Resource.Success -> {
                        setState(MapUiState())
                    }

                    is Resource.Error -> {
                    }
                }
            }
        }
    }

    fun updateContent(text: String){
        setState(currentState.copy(sheetData = currentState.sheetData.copy(text = text)))
    }

}

data class MapUiState(
    val currentLocation: LatLng = LatLng(0.0, 0.0),
    val items: List<Item> = listOf(),
    val sheetData: SheetData = SheetData()
) : UiState

sealed class MapUiEvent : UiEvent {
    data class InitCurrentLocation(val latitude: Double, val longitude: Double) : MapUiEvent()
    object FetchItemList : MapUiEvent()
    object OnClickDropItem : MapUiEvent()
    data class OnSelectLevel(val type: ItemType) : MapUiEvent()
    object OnClickRegister : MapUiEvent()
}

sealed class MapUiEffect : UiEffect {

}

data class SheetData(
    val step: Step = Step.INIT,
    val level: ItemType? = null,
    val text: String = ""
)

enum class Step { INIT, LEVEL, CONTENT }
