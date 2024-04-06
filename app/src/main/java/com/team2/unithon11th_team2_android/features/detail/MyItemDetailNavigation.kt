package com.team2.unithon11th_team2_android.features.detail

import android.annotation.SuppressLint
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.team2.unithon11th_team2_android.features.permission.PERMISSION_ROUTE
import com.team2.unithon11th_team2_android.features.permission.PermissionScreen

const val MY_ITEM_DETAIL = "myItemDetail"

fun NavHostController.navigateToMyItemDetail() = navigate(MY_ITEM_DETAIL)

fun NavGraphBuilder.myItemDetailScreen(
    onBackPressed: () -> Unit
){
    composable(MY_ITEM_DETAIL){
        MyItemDetailScreen(onBackPressed)
    }
}