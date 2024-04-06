package com.team2.unithon11th_team2_android.features.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.R
import com.team2.unithon11th_team2_android.common.ui.theme.OurTypo
import com.team2.unithon11th_team2_android.component.contract.LoginContract
import timber.log.Timber

@Composable
internal fun LoginScreen(
    navController: NavHostController, loginViewModel: LoginViewModel = hiltViewModel()
) {

    val nickname = loginViewModel.nickname.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 20.dp)
            .padding(top = 44.dp)
    ) {
        Text(
            text = stringResource(R.string.login_header_title),
            style = OurTypo.current.Heading,
            modifier = Modifier
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = nickname,
            onValueChange = {
                loginViewModel.setEvent(
                    LoginContract.Event.OnFetchNickname(it)
                )
                Timber.e("#### ${it}")
            }
        )

        Text(
            text = stringResource(R.string.login_hint_explain),
            style = OurTypo.current.Caption,
            modifier = Modifier
        )

        Button(onClick = {
            loginViewModel.setEvent(
                LoginContract.Event.OnNextButtonClicked
            )
        }, modifier = Modifier.fillMaxWidth()) {
            Text(text = "다음")
        }
    }
}

@Preview
@Composable
fun LoginPreview() {
    LoginScreen(navController = rememberNavController())
}