package com.team2.unithon11th_team2_android.features.home

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val HOME_ROUTE = "home"


fun NavHostController.navigateToHomeScreen() = navigate(HOME_ROUTE)

fun NavGraphBuilder.homeScreen(){
    composable(HOME_ROUTE) {
        HomeScreen()
    }
}