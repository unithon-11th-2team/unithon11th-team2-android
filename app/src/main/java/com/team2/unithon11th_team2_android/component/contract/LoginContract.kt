package com.team2.unithon11th_team2_android.component.contract

import androidx.compose.runtime.MutableState
import com.team2.unithon11th_team2_android.common.base.UiEffect
import com.team2.unithon11th_team2_android.common.base.UiEvent
import com.team2.unithon11th_team2_android.common.base.UiState
import kotlinx.coroutines.flow.MutableSharedFlow

class LoginContract {
    sealed class Event : UiEvent {
        data class OnFetchNickname(val nickname: String) : Event()
        object OnNextButtonClicked : Event()

    }

    data class State(
        val nickname : MutableState<String?>
    ) : UiState

    sealed class Effect : UiEffect {
        data class ShowToast(val message: String) : Effect()
    }
}

object LoginUiEvent {
    val uiEffect = MutableSharedFlow<MainContract.Effect>(extraBufferCapacity = 1)

    suspend fun showToast(message: String) {
        uiEffect.emit(MainContract.Effect.ShowToast(message))
    }
}