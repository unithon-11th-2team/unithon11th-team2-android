package com.team2.unithon11th_team2_android.features.ranking

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val RANKING_ROUTE = "ranking"

fun NavHostController.navigateToRankingScreen() = navigate(RANKING_ROUTE)

fun NavGraphBuilder.rankingScreen() {
    composable(route = RANKING_ROUTE) { RankingScreen() }
}