package com.team2.unithon11th_team2_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme
import com.team2.unithon11th_team2_android.component.Toast
import com.team2.unithon11th_team2_android.component.contract.MainContract
import com.team2.unithon11th_team2_android.component.contract.MainUiEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupSplashScreen()

        setContent {
            mainViewModel.setEvent(MainContract.Event.GetLastRoute)
            var toastState by remember { mutableStateOf("") }
            var job: Job? = null
            val scope = rememberCoroutineScope()
            OurTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(key1 = Unit) {
                        MainUiEvent.uiEffect.collect {
                            Timber.d("### Collected MainContract Effect - $it")
                            when (it) {
                                is MainContract.Effect.ShowToast -> {
                                    job?.cancel()
                                    job = scope.launch {
                                        if (it.message.isNotBlank()) {
                                            toastState = it.message
                                            delay(2000L)
                                            toastState = ""
                                        }
                                    }
                                }
                            }
                        }
                    }
                    mainViewModel.uiState.collectAsState().value.startDestination.value?.let {
                        NavHost(startDestination = it, finish = {
                            Timber.e("FINISH")
                        })
                    }
                    Toast(
                        modifier = Modifier,
                        message = toastState,
                        isVisible = toastState.isNotEmpty()
                    )
                }
            }
        }
    }

    private fun setupSplashScreen() {
        var keepSplashScreenOn = true
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                mainViewModel.uiState.collect {
                    Timber.e("$$$$ - ${it.isLoading.value}")
                    delay(800)
                    Timber.e("${it.isLoading.value}")
                    keepSplashScreenOn = it.isLoading.value
                }
            }
        }

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreenOn
        }
    }
}
