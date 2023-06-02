package android.skills_market.ui.navigation

import android.skills_market.ui.activities.screens.LogRegScreen
import android.skills_market.ui.activities.screens.LoginScreen
import android.skills_market.ui.activities.screens.RegistrationScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LogRegNavigationGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LogRegScreen.route) {
        composable(route = Screen.LogRegScreen.route) {
            LogRegScreen(navController = navController)
        }
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.RegisterScreen.route) {
            RegistrationScreen(navController = navController)
        }
    }
}