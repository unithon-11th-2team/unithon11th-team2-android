package com.team2.unithon11th_team2_android

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.features.home.HomeScreen
import com.team2.unithon11th_team2_android.features.login.LoginScreen

@SuppressLint("MissingPermission")
@Composable
fun NavHost(
    navController: NavHostController = rememberNavController(),
    startDestination: String = NavRoutes.Login.route,
    finish: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavRoutes.Login.route) {
            LoginScreen(navController)
        }
        composable(NavRoutes.Home.route) {
            HomeScreen(finish)
        }
    }
}