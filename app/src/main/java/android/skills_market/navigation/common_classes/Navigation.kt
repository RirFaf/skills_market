package android.skills_market.navigation.common_classes

import android.content.Context
import android.skills_market.navigation.screens.ChatListScreen
import android.skills_market.navigation.screens.LoginScreen
import android.skills_market.navigation.screens.ProfileScreen
import android.skills_market.navigation.screens.RegisterScreen
import android.skills_market.navigation.screens.ResponsesListScreen
import android.skills_market.navigation.screens.SearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation(localContext: Context) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route){
            LoginScreen(navController = navController, localContext = localContext)
        }
        composable(route = Screen.RegisterScreen.route){ entry ->
            RegisterScreen(navController = navController)
        }
        composable(route = Screen.SearchScreen.route){
            SearchScreen(navController = navController)
        }
        composable(route = Screen.ChatListScreen.route){
            ResponsesListScreen(navController = navController)
        }
        composable(route = Screen.ResponsesListScreen.route){
            ChatListScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(navController = navController)
        }
    }
}