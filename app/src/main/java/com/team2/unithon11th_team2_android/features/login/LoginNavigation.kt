package com.team2.unithon11th_team2_android.features.login

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.team2.unithon11th_team2_android.features.permission.PERMISSION_ROUTE
import com.team2.unithon11th_team2_android.features.permission.PermissionScreen

const val LOGIN_ROUTE = "login"

fun NavHostController.navigateToLogin() = navigate(LOGIN_ROUTE)

fun NavGraphBuilder.loginScreen(
    navigateToHomeScreen: () -> Unit
){
    composable(LOGIN_ROUTE){
        LoginScreen(onSuccessLogin = navigateToHomeScreen)
    }
}