package com.team2.unithon11th_team2_android.features.map

import android.annotation.SuppressLint
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val MAP_ROUTE = "map"

fun NavHostController.navigateToMapScreen() = navigate(MAP_ROUTE)

@SuppressLint("MissingPermission")
fun NavGraphBuilder.mapScreen(finish: () -> Unit){
    composable(MAP_ROUTE) {
        MapScreen(onBackPressed = finish)
    }
}