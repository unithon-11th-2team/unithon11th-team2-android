package com.team2.unithon11th_team2_android.features.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val LOGIN_ROUTE = "login"

fun NavHostController.navigateToLogin() = navigate(LOGIN_ROUTE)

fun NavGraphBuilder.loginScreen(
    navigateToHomeScreen: () -> Unit,
    finish: () -> Unit
) {
    composable(LOGIN_ROUTE) {
        LoginScreen(onSuccessLogin = navigateToHomeScreen) {
            finish()
        }
    }
}