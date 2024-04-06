package com.team2.unithon11th_team2_android

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.component.contract.MainContract
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class MainViewModel @Inject constructor(
) : BaseViewModel<MainContract.State, MainContract.Event, MainContract.Effect>() {
    override fun createInitialState(): MainContract.State {
        return MainContract.State(
            isLoading = mutableStateOf(true), startDestination = mutableStateOf("")
        )
    }

    override fun handleEvent(event: MainContract.Event) {
        when (event) {
            is MainContract.Event.GetLastRoute -> {
                getLastRoute()
            }
        }
    }

    private fun getLastRoute() {
        viewModelScope.launch {
            setState(
                currentState.copy(
                    isLoading = mutableStateOf(false),
                    startDestination = mutableStateOf(NavRoutes.Login.route)
                )
            )
        }
    }

}