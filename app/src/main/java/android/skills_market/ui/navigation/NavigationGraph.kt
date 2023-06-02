package android.skills_market.ui.navigation

import android.skills_market.ui.activities.screens.ChatListScreen
import android.skills_market.ui.activities.screens.FavouritesScreen
import android.skills_market.ui.activities.screens.ProfileScreen
import android.skills_market.ui.activities.screens.ResponsesListScreen
import android.skills_market.ui.activities.screens.SearchScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationGraph(navController: NavHostController) {
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
            ProfileScreen(navController = navController)
        }
    }
}