package com.team2.unithon11th_team2_android

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.team2.domain.common.Resource
import com.team2.domain.usecase.AutoLoginUseCase
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.common.base.UiEffect
import com.team2.unithon11th_team2_android.common.base.UiEvent
import com.team2.unithon11th_team2_android.common.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
    private val autoLoginUseCase: AutoLoginUseCase,
) : BaseViewModel<MainUiState, UiEvent, UiEffect>() {
    override fun createInitialState(): MainUiState {
        return MainUiState(mutableStateOf(true), TargetRoute.PERMISSION)
    }

    init {
        setState(
            currentState.copy(
                route = if (hasToken()) {
                    Timber.e("### HOME")
                    TargetRoute.HOME
                } else {
                    Timber.e("### PERMISSION")
                    TargetRoute.PERMISSION
                },
                isLoading = mutableStateOf(false)
            )
        )
    }

    private fun hasToken(): Boolean {
        return runBlocking {
            (autoLoginUseCase().firstOrNull() as? Resource.Success)?.data ?: false
        }
    }

    override fun handleEvent(event: UiEvent) {
    }
}

data class MainUiState(
    val isLoading: MutableState<Boolean>,
    val route: TargetRoute
) : UiState

enum class TargetRoute {
    PERMISSION, HOME
}