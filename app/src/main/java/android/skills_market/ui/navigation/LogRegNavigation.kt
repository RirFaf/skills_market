package android.skills_market.ui.navigation

import android.skills_market.ui.activities.screens.LogRegScreen
import android.skills_market.ui.activities.screens.LoginScreen
import android.skills_market.ui.activities.screens.RegistrationScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.navigation.animation.AnimatedNavHost

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

@Composable
fun AnimatedRegGraph(navController: NavHostController, startDestination: String) {
//    AnimatedNavHost(navController = navController, startDestination = startDestination) {
//        composable(route){
//
//        }
//    }
}
