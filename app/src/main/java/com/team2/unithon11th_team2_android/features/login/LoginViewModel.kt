package com.team2.unithon11th_team2_android.features.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.team2.domain.common.Resource
import com.team2.domain.usecase.LoginUseCase
import com.team2.unithon11th_team2_android.common.base.BaseViewModel
import com.team2.unithon11th_team2_android.component.contract.LoginContract
import com.team2.unithon11th_team2_android.component.contract.LoginUiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : BaseViewModel<LoginContract.State, LoginContract.Event, LoginContract.Effect>() {

    private val _nickname = MutableStateFlow("")
    val nickname = _nickname.asStateFlow()
    override fun createInitialState(): LoginContract.State {
        return LoginContract.State(
            nickname = mutableStateOf("")
        )
    }

    override fun handleEvent(event: LoginContract.Event) {
        when (event) {
            is LoginContract.Event.OnFetchNickname -> {
                setNickname(event.nickname)
            }

            is LoginContract.Event.OnNextButtonClicked -> {
                signUp()
            }
        }
    }

    private fun setNickname(nickname: String) {
        Timber.e("#### setNickName $nickname")
        _nickname.value = nickname
    }

    private fun signUp() {
        viewModelScope.launch {
            loginUseCase.invoke(_nickname.value).collect{
                when(it) {
                    is Resource.Success -> {

                        Timber.e("SUCCESS ${it}")
                    }
                    is Resource.Error -> {
                        Timber.e("ERROR")
                    }
                }
            }
        }
    }

}