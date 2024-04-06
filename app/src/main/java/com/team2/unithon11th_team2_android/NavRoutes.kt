package com.team2.unithon11th_team2_android

sealed class NavRoutes(val route: String) {
    object Home : NavRoutes("home")
    object Login : NavRoutes("login")
}