package android.skills_market.ui.navigation

import android.skills_market.ui.activities.screens.CityCourseAndPhone
import android.skills_market.ui.activities.screens.EmailAndPasswordScreen
import android.skills_market.ui.activities.screens.LogRegScreen
import android.skills_market.ui.activities.screens.LoginScreen
import android.skills_market.ui.activities.screens.NameAndGenderRegScreen
import android.skills_market.ui.activities.screens.RegistrationScreen
import android.skills_market.view_models.RegViewModel
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
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
        composable(route = Screen.RegistrationScreen.route) {
            RegistrationScreen(navController = navController)
        }
    }
}

@Composable
fun RegGraph(
    navController: NavHostController,
    viewModel: RegViewModel
) {
    NavHost(navController = navController, startDestination = Screen.NameAndGenderRegScreen.route) {
        composable(route = Screen.NameAndGenderRegScreen.route) {
            NameAndGenderRegScreen(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.CityCourseAndPhone.route) {
            CityCourseAndPhone(viewModel = viewModel, navController = navController)
        }
        composable(route = Screen.EmailAndPasswordScreen.route) {
            EmailAndPasswordScreen(viewModel = viewModel)
        }
    }
}
