package android.skills_market.ui.navigation

import android.skills_market.network.models.SelectedVacancyModel
import android.skills_market.network.models.VacanciesModel
import android.skills_market.ui.screens.ChatListScreen
import android.skills_market.ui.screens.FavouritesScreen
import android.skills_market.ui.screens.MessengerScreen
import android.skills_market.ui.screens.ProfileScreen
import android.skills_market.ui.screens.ResponsesListScreen
import android.skills_market.ui.screens.SearchScreen
import android.skills_market.ui.screens.VacancyScreen
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    val customEnterTransition: EnterTransition? =
        fadeIn(
            animationSpec = tween(150, easing = LinearEasing)
        )
    val customExitTransition: ExitTransition? =
        fadeOut(
            animationSpec = tween(150, easing = LinearEasing)
        )

    NavHost(
        navController = navController,
        startDestination = Screen.SearchScreen.route
    ) {
        composable(
            route = Screen.SearchScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            SearchScreen(navController = navController, vacancies = VacanciesModel().vacancies)
        }
        composable(
            route = Screen.VacancyScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            VacancyScreen(
                navController = navController,
                /***
                Для тестирования
                 ***/
                vacancy = SelectedVacancyModel(
                    id = 0,
                    position = "Бухгалтер",
                    salary = 100000,
                    companyName = "АкБарс",
                    edArea = "Юриспрюденция",
                    formOfEmployment = "Частичная",
                    requirements = "2 курса",
                    location = "Казань, ст. Козья слобода",
                )
            )
        }
        composable(
            route = Screen.FavouritesScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            FavouritesScreen(navController = navController)
        }
        composable(
            route = Screen.ChatListScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            ChatListScreen(navController = navController)
        }
        composable(
            route = Screen.MessengerScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(0, easing = LinearEasing)
                )
            },
            popEnterTransition = { customEnterTransition },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(0, easing = LinearEasing)
                )
            },
        ) {
            MessengerScreen(navController = navController)
        }

        composable(
            route = Screen.ResponsesListScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            ResponsesListScreen(navController = navController)
        }
        composable(
            route = Screen.ProfileScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            ProfileScreen(navController = navController)
        }
    }
}

