import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.team2.unithon11th_team2_android.features.permission.permissionScreen
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.AppRoute
import com.team2.unithon11th_team2_android.OurApp
import com.team2.unithon11th_team2_android.features.login.LOGIN_ROUTE
import com.team2.unithon11th_team2_android.features.login.loginScreen
import com.team2.unithon11th_team2_android.features.login.navigateToLogin
import com.team2.unithon11th_team2_android.features.map.mapScreen
import com.team2.unithon11th_team2_android.features.map.navigateToMapScreen

@Composable
fun OurNavHost(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = LOGIN_ROUTE
    ) {
        composable(AppRoute.ourPeace) {
            OurApp(navController)
        }
        loginScreen(navController::navigateToMapScreen)
        mapScreen {  }
        permissionScreen(navController::navigateToLogin)
    }
}