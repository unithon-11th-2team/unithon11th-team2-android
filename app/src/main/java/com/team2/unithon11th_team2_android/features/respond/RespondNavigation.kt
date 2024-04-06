package com.team2.unithon11th_team2_android.features.respond

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val RESPOND_ROUTE = "respond"

fun NavHostController.navigateToRespondScreen() = navigate(RESPOND_ROUTE)

fun NavGraphBuilder.respondScreen(){
    composable(RESPOND_ROUTE) {
        RespondScreen()
    }
}