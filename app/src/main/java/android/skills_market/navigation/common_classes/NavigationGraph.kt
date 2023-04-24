package android.skills_market.navigation.common_classes

import android.content.Context
import android.skills_market.navigation.screens.ChatListScreen
import android.skills_market.navigation.screens.FavouritesScreen
import android.skills_market.navigation.screens.LoginScreen
import android.skills_market.navigation.screens.ProfileScreen
import android.skills_market.navigation.screens.RegisterScreen
import android.skills_market.navigation.screens.ResponsesListScreen
import android.skills_market.navigation.screens.SearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(localContext: Context, navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.SearchScreen.route) {

        composable(route = Screen.SearchScreen.route){
            SearchScreen(navController = navController)
        }
        composable(route = Screen.FavouritesScreen.route){
            FavouritesScreen(navController = navController)
        }
        composable(route = Screen.ChatListScreen.route){
            ChatListScreen(navController = navController)
        }
        composable(route = Screen.ResponsesListScreen.route){
            ResponsesListScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route){
            ProfileScreen(navController = navController, localContext = localContext)
        }
    }
}