package com.team2.unithon11th_team2_android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.common.ui.theme.OurTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OurTheme {
                val navController = rememberNavController()
                NavHost(navController = navController){
                    finish()
                }
            }
        }
    }
}