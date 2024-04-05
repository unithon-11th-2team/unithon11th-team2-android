package com.team2.unithon11th_team2_android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.team2.unithon11th_team2_android.features.home.HOME_ROUTE
import com.team2.unithon11th_team2_android.features.home.homeScreen

@Composable
fun NavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = HOME_ROUTE) {
        homeScreen()
    }
}