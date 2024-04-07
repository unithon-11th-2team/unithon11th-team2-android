import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.AppRoute
import com.team2.unithon11th_team2_android.OurApp
import com.team2.unithon11th_team2_android.features.detail.myItemDetailScreen
import com.team2.unithon11th_team2_android.features.login.loginScreen
import com.team2.unithon11th_team2_android.features.login.navigateToLogin
import com.team2.unithon11th_team2_android.features.map.mapScreen
import com.team2.unithon11th_team2_android.features.map.navigateToMapScreen
import com.team2.unithon11th_team2_android.features.myitem.myItemListScreen
import com.team2.unithon11th_team2_android.features.myitem.navigateToMyItemList
import com.team2.unithon11th_team2_android.features.permission.permissionScreen
import com.team2.unithon11th_team2_android.features.respond.navigateToRespondScreen
import com.team2.unithon11th_team2_android.features.respond.respondScreen


@Composable
fun OurNavHost(
    navController: NavHostController = rememberNavController(),
    finish: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = AppRoute.ourPeace
    ) {
        composable(AppRoute.ourPeace) {
            OurApp(navController)
        }
        loginScreen(navController::navigateToMapScreen) {
            finish()
        }
        mapScreen(
            navigateToRespond = { id ->
                navController.navigateToRespondScreen(id)
            },
            navigateToMyItemList = navController::navigateToMyItemList
        ) { finish() }
        permissionScreen(navController::navigateToLogin)
        myItemListScreen(navController::navigateToMapScreen, navController::navigateToMapScreen)
        myItemDetailScreen(navController::navigateToMyItemList)
        respondScreen()
    }
}