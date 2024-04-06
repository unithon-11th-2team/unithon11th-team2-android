package com.team2.unithon11th_team2_android

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.team2.unithon11th_team2_android.features.map.mapScreen
import com.team2.unithon11th_team2_android.features.map.navigateToMapScreen
import com.team2.unithon11th_team2_android.features.permission.PERMISSION_ROUTE
import com.team2.unithon11th_team2_android.features.permission.permissionScreen

@Composable
fun NavHost(navController: NavHostController, finish: () -> Unit) {
    NavHost(navController = navController, startDestination = PERMISSION_ROUTE) {
        permissionScreen(navController::navigateToMapScreen)
        mapScreen(finish)
    }
}