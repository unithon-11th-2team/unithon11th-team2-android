package com.team2.unithon11th_team2_android.features.respond

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val RESPOND_ROUTE = "respond"

fun NavHostController.navigateToRespondScreen(id: Int) = navigate("$RESPOND_ROUTE/$id")

fun NavGraphBuilder.respondScreen() {
    composable(
        route = "$RESPOND_ROUTE/{id}",
        arguments = listOf(navArgument("id"){ type = NavType.IntType })
    ) { RespondScreen() }
}