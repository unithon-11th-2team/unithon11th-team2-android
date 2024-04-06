package com.team2.unithon11th_team2_android.features.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.theme.OurColorPalette
import com.team2.unithon11th_team2_android.common.ui.theme.OurTypo
import com.team2.unithon11th_team2_android.component.AppBarWithBackNavigation
import com.team2.unithon11th_team2_android.component.MainButton
import com.team2.unithon11th_team2_android.component.TitleText
import timber.log.Timber

@Composable
internal fun LoginScreen(
    navController: NavController,
    onSuccessLogin: () -> Unit,
    loginViewModel: LoginViewModel = hiltViewModel()
) {
    val nickname = loginViewModel.nickname.collectAsState().value
    val state = loginViewModel.uiState.collectAsState().value

    when (state.state) {
        is LoginState.SUCCESS -> {
            if ((state.state as LoginState.SUCCESS).newUser) {
                onSuccessLogin()
            } else {
                navController.popBackStack()
            }
        }

        is LoginState.FAILED -> {
            // TODO error handling
        }

        else -> {}
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .padding(vertical = 56.dp)
    ) {

       Image(
           painter = painterResource(R.drawable.profile_title_img), contentDescription = null)
        Spacer(modifier = Modifier.padding(20.dp))
        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .background(Color.White)
                .padding()
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(4.dp))
                .padding(horizontal = 4.dp, vertical = 4.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                if (nickname.isEmpty()) {
                    Text(
                        text = stringResource(id = R.string.login_explain),
                        style = OurTypo.current.Body01.copy(color = OurColorPalette.current.lightGray)
                    )
                }
                innerTextField()

            },
            value = nickname,
            onValueChange = {
                Timber.e("#### ${it}")

                loginViewModel.setEvent(
                    LoginUiEvent.OnFetchNickname(it)
                )
            }
        )

        Text(
            text = stringResource(R.string.login_hint_explain),
            style = OurTypo.current.Caption,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.padding(6.dp))
        MainButton(
            title = "다음",
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            onClickAction = {
                loginViewModel.setEvent(
                    LoginUiEvent.OnClickLoginButton
                )
            })
    }


}
