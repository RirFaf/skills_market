package android.skills_market.ui.navigation

import android.skills_market.data.VacancyModel
import android.skills_market.ui.screens.ChatListScreen
import android.skills_market.ui.screens.FavouritesScreen
import android.skills_market.ui.screens.MessengerScreen
import android.skills_market.ui.screens.ProfileScreen
import android.skills_market.ui.screens.ResponsesListScreen
import android.skills_market.ui.screens.SearchScreen
import android.skills_market.ui.screens.VacancyScreen
import android.skills_market.view_models.VacancyViewModel
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.SearchScreen.route
    ) {
        composable(route = Screen.SearchScreen.route) {
            SearchScreen(navController = navController)
        }
        composable(route = Screen.VacancyScreen.route) {
            val vacancyViewModel =
                navController.previousBackStackEntry?.savedStateHandle?.get<VacancyViewModel>("vacancy")

            VacancyScreen(navController = navController, vacancyViewModel!!)
        }
        composable(route = Screen.FavouritesScreen.route) {
            FavouritesScreen(navController = navController)
        }
        composable(route = Screen.ChatListScreen.route) {
            ChatListScreen(navController = navController)
        }
        composable(route = Screen.MessengerScreen.route) {
            MessengerScreen(navController = navController)
        }

        composable(route = Screen.ResponsesListScreen.route) {
            ResponsesListScreen(navController = navController)
        }
        composable(route = Screen.ProfileScreen.route) {
            ProfileScreen(navController = navController)
        }
    }
}