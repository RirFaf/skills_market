package android.skills_market.ui.navigation

import android.skills_market.data.network.models.CompanyModel
import android.skills_market.data.network.models.VacancyModel
import android.skills_market.ui.navigation.extensions.sharedViewModel
import android.skills_market.ui.screens.messenger.ChatListScreen
import android.skills_market.ui.screens.FavouritesScreen
import android.skills_market.ui.screens.messenger.MessengerScreen
import android.skills_market.ui.screens.profile.ProfileScreen
import android.skills_market.ui.screens.ResponsesListScreen
import android.skills_market.ui.screens.profile.ProfileRedactorScreen
import android.skills_market.ui.screens.SearchScreen
import android.skills_market.ui.screens.VacancyScreen
import android.skills_market.ui.screens.resume.ResumeRedactorScreen
import android.skills_market.ui.screens.resume.ResumeScreen
import android.skills_market.view_model.FavouritesViewModel
import android.skills_market.view_model.MessengerViewModel
import android.skills_market.view_model.ProfileViewModel
import android.skills_market.view_model.ResponsesViewModel
import android.skills_market.view_model.ResumeViewModel
import android.skills_market.view_model.SearchViewModel
import android.skills_market.view_model.VacancyViewModel
import android.skills_market.view_model.event.MessengerEvent
import android.skills_market.view_model.event.VacancyEvent
import android.widget.Toast
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationGraph(navController: NavHostController) {
    val customEnterTransition: EnterTransition =
        fadeIn(
            animationSpec = tween(150, easing = LinearEasing)
        )
    val customExitTransition: ExitTransition =
        fadeOut(
            animationSpec = tween(150, easing = LinearEasing)
        )
    val localContext = LocalContext.current
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
            val searchViewModel = viewModel<SearchViewModel>(
                factory = SearchViewModel.Factory
            )
            val state by searchViewModel.uiState.collectAsStateWithLifecycle()
            SearchScreen(
                navController = navController,
                onEvent = searchViewModel::onEvent,
                state = state
            )
        }
        composable(
            route = Screen.VacancyScreen.route +
                    "/{id}" +
                    "/{position}" +
                    "/{salary}" +
                    "/{companyId}" +
                    "/{companyName}" +
                    "/{edArea}" +
                    "/{formOfEmployment}" +
                    "/{requirements}" +
                    "/{location}" +
                    "/{about}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                },
                navArgument(name = "position") {
                    type = NavType.StringType
                },
                navArgument(name = "salary") {
                    type = NavType.IntType
                },
                navArgument(name = "companyId") {
                    type = NavType.StringType
                },
                navArgument(name = "companyName") {
                    type = NavType.StringType
                },
                navArgument(name = "edArea") {
                    type = NavType.StringType
                },
                navArgument(name = "formOfEmployment") {
                    type = NavType.StringType
                },
                navArgument(name = "requirements") {
                    type = NavType.StringType
                },
                navArgument(name = "location") {
                    type = NavType.StringType
                },
                navArgument(name = "about") {
                    type = NavType.StringType
                },
            ),
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) { entry ->
            val vacancyViewModel = viewModel<VacancyViewModel>(
                factory = VacancyViewModel.Factory
            )
            val state by vacancyViewModel.uiState.collectAsStateWithLifecycle()
            vacancyViewModel.onEvent(
                VacancyEvent.SetVacancy(
                    VacancyModel(
                        id = entry.arguments?.getString("id")!!,
                        position = entry.arguments?.getString("position")!!,
                        salary = entry.arguments?.getInt("salary")!!,
                        company = CompanyModel(
                            entry.arguments?.getString("companyId")!!,
                            entry.arguments?.getString("companyName")!!
                        ),
                        edArea = entry.arguments?.getString("edArea")!!,
                        formOfEmployment = entry.arguments?.getString("formOfEmployment")!!,
                        requirements = entry.arguments?.getString("requirements")!!,
                        location = entry.arguments?.getString("location")!!,
                        about = entry.arguments?.getString("about")!!
                    )
                )
            )
            VacancyScreen(
                navController = navController,
                state = state,
                onEvent = vacancyViewModel::onEvent
            )
        }

        composable(
            route = Screen.FavouritesScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            val favouritesViewModel = viewModel<FavouritesViewModel>(
                factory = FavouritesViewModel.Factory
            )
            val state by favouritesViewModel.uiState.collectAsStateWithLifecycle()
            FavouritesScreen(
                navController = navController,
                onEvent = favouritesViewModel::onEvent,
                state = state
            )
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
            route = Screen.MessengerScreen.route +
                    "/{id}" +
                    "/{position}" +
                    "/{salary}" +
                    "/{companyId}" +
                    "/{companyName}" +
                    "/{edArea}" +
                    "/{formOfEmployment}" +
                    "/{requirements}" +
                    "/{location}" +
                    "/{about}",
            arguments = listOf(
                navArgument(name = "id") {
                    type = NavType.StringType
                },
                navArgument(name = "position") {
                    type = NavType.StringType
                },
                navArgument(name = "salary") {
                    type = NavType.IntType
                },
                navArgument(name = "companyId") {
                    type = NavType.StringType
                },
                navArgument(name = "companyName") {
                    type = NavType.StringType
                },
                navArgument(name = "edArea") {
                    type = NavType.StringType
                },
                navArgument(name = "formOfEmployment") {
                    type = NavType.StringType
                },
                navArgument(name = "requirements") {
                    type = NavType.StringType
                },
                navArgument(name = "location") {
                    type = NavType.StringType
                },
                navArgument(name = "about") {
                    type = NavType.StringType
                },
            ),
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
        ) { entry ->
            val messengerViewModel = viewModel<MessengerViewModel>(
                factory = MessengerViewModel.Factory
            )
            val state by messengerViewModel.uiState.collectAsStateWithLifecycle()
            messengerViewModel.onEvent(
                MessengerEvent.GetMessages(
                    receiverId = entry.arguments?.getString(
                        "companyId"
                    )!!,
                    onFailureAction = {
                        Toast.makeText(localContext, "Что-то пошло не так", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
            )
            MessengerScreen(
                navController = navController,
                onEvent = messengerViewModel::onEvent,
                state = state
            )
        }

        composable(
            route = Screen.ResponsesListScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
            val responsesViewModel =
                viewModel<ResponsesViewModel>(factory = ResponsesViewModel.Factory)
            val state by responsesViewModel.uiState.collectAsStateWithLifecycle()
            ResponsesListScreen(
                navController = navController,
                state = state,
                onEvent = responsesViewModel::onEvent
            )
        }

        composable(
            route = Screen.ProfileScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) { entry ->
            val profileViewModel = entry.sharedViewModel<ProfileViewModel>(
                factory = ProfileViewModel.Factory,
                navController = navController
            )
            val state by profileViewModel.uiState.collectAsStateWithLifecycle()
            ProfileScreen(
                navController = navController,
                state = state,
                onEvent = profileViewModel::onEvent
            )
        }

        composable(
            route = Screen.ProfileRedactorScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) { entry ->
            val profileViewModel = entry.sharedViewModel<ProfileViewModel>(
                factory = ProfileViewModel.Factory,
                navController = navController
            )
            val state by profileViewModel.uiState.collectAsStateWithLifecycle()
            ProfileRedactorScreen(
                navController = navController,
                state = state,
                onEvent = profileViewModel::onEvent
            )
        }

        composable(
            route = Screen.ResumeScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) { entry ->
            val resumeViewModel = entry.sharedViewModel<ResumeViewModel>(
                navController = navController,
                factory = ResumeViewModel.Factory
            )
            val state by resumeViewModel.uiState.collectAsStateWithLifecycle()
            ResumeScreen(
                navController = navController,
                state = state,
                onEvent = resumeViewModel::onEvent
            )
        }
        composable(
            route = Screen.ResumeRedactorScreen.route,
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) { entry ->
            val resumeViewModel = entry.sharedViewModel<ResumeViewModel>(
                navController = navController,
                factory = ResumeViewModel.Factory
            )
            val state by resumeViewModel.uiState.collectAsStateWithLifecycle()
            ResumeRedactorScreen(
                navController = navController,
                state = state,
                onEvent = resumeViewModel::onEvent
            )
        }
    }
}

