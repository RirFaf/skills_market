package android.skills_market.navigation.common_classes

import android.content.Context
import android.skills_market.navigation.screens.LoginScreen
import android.skills_market.navigation.screens.RegisterScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LogRegNavigationGraph(localContext: Context, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController, localContext = localContext)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegisterScreen(navController = navController, localContext = localContext)
        }
    }
}