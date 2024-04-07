package com.team2.unithon11th_team2_android.features.login

import androidx.lifecycle.viewModelScope
import com.team2.domain.common.Resource
import com.team2.domain.usecase.AutoLoginUseCase
import com.team2.domain.usecase.LoginUseCase
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.common.base.UiEffect
import com.team2.unithon11th_team2_android.common.base.UiEvent
import com.team2.unithon11th_team2_android.common.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val autoLoginUseCase: AutoLoginUseCase
) : BaseViewModel<LoginUiState, LoginUiEvent, UiEffect>() {

    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()
    override fun createInitialState(): LoginUiState {
        return LoginUiState(LoginState.BEFORE, "")
    }

    override fun handleEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.OnFetchNickname -> {
                setNickname(event.nickname)
            }

            is LoginUiEvent.OnClickLoginButton -> {
                signUp()
            }

            is LoginUiEvent.OnCheckLogin -> {
                login()
            }

            else -> {}
        }
    }

    private fun login() {
        viewModelScope.launch {
            autoLoginUseCase.invoke().collect {
                when (it) {
                    is Resource.Success -> {
                        Timber.e("#### SUCCESS ${it.data}")
                        setState(currentState.copy(LoginState.SUCCESS(false), nickname = _nickname.value))
                    }

                    is Resource.Error -> {
                        Timber.e("ERROR ${it}")
                        // TODO Failed로 변경
                        setState(currentState.copy(LoginState.SUCCESS(false), nickname = _nickname.value))
                    }

                    else -> {}
                }
            }
        }
    }

    private fun setNickname(nickname: String) {
        Timber.e("#### setNickName $nickname")
        _nickname.value = nickname
    }

    private fun signUp() {
        viewModelScope.launch {
            loginUseCase.invoke(_nickname.value).collect {
                when (it) {
                    is Resource.Success -> {
                        Timber.e("SUCCESS ${it}")
                        setState(currentState.copy(LoginState.SUCCESS(true), nickname = _nickname.value))
                    }

                    is Resource.Error -> {
                        Timber.e("ERROR ${it}")
                        // TODO Failed로 변경
                        setState(currentState.copy(LoginState.SUCCESS(true), nickname = _nickname.value))
                    }

                    else -> {}
                }
            }
        }
    }
}

data class LoginUiState(
    val state: LoginState,
    val nickname: String
) : UiState

sealed class LoginState {
    object BEFORE : LoginState()
    data class SUCCESS(val newUser: Boolean) : LoginState()
    object FAILED : LoginState()
}

sealed class LoginUiEvent : UiEvent {
    object OnCheckLogin: LoginUiEvent()
    object OnClickLoginButton : LoginUiEvent()
    data class OnFetchNickname(val nickname: String) : LoginUiEvent()
}