import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.team2.unithon11th_team2_android.AppRoute
import com.team2.unithon11th_team2_android.OurApp
import com.team2.unithon11th_team2_android.features.home.homeScreen
import com.team2.unithon11th_team2_android.features.home.navigateToHomeScreen
import com.team2.unithon11th_team2_android.features.login.LOGIN_ROUTE
import com.team2.unithon11th_team2_android.features.login.loginScreen
import com.team2.unithon11th_team2_android.features.login.navigateToLogin
import com.team2.unithon11th_team2_android.features.permission.permissionScreen

@Composable
fun OurNavHost(
    navController: NavHostController = rememberNavController()
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = LOGIN_ROUTE
    ) {
        composable(AppRoute.ourPeace) {
            OurApp(navController)
        }
        loginScreen(navController::navigateToHomeScreen)
        homeScreen {  }
        permissionScreen(navController::navigateToLogin)
    }
}