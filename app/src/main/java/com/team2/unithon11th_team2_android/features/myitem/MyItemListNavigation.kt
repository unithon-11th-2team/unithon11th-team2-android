package com.team2.unithon11th_team2_android.features.myitem

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable

const val MY_ITEM_LIST = "myItemList"

fun NavHostController.navigateToMyItemList() = navigate(MY_ITEM_LIST)

fun NavGraphBuilder.myItemListScreen(
    onBackPressed: () -> Unit,
    navigateToMyItemDetail: () -> Unit
){
    composable(MY_ITEM_LIST){
        MyItemListScreen(onBackPressed, navigateToMyItemDetail)
    }
}