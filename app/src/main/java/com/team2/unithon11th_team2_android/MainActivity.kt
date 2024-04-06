package com.team2.unithon11th_team2_android

import OurNavHost
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.team2.unithon11th_team2_android.common.ui.theme.OurColorPalette
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme
import com.team2.unithon11th_team2_android.features.map.MAP_ROUTE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OurTheme {
                OurNavHost()
            }
        }
    }
}

@Composable
internal fun OurApp(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(), color = OurColorPalette.current.background
    ) {
        when (state.route) {
            TargetRoute.PERMISSION -> {
                navController.navigate(AppRoute.permission)
            }

            TargetRoute.HOME -> {
                navController.navigate(MAP_ROUTE)
            }
        }
    }
}
