package com.team2.unithon11th_team2_android.features.permission

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val PERMISSION_ROUTE = "permission"

fun NavGraphBuilder.permissionScreen(
    navigateToLogin: () -> Unit
){
    composable(PERMISSION_ROUTE){
        PermissionScreen(navigateToLogin)
    }
}