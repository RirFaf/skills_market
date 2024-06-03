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
import android.util.Log
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
import androidx.navigation.navigation
import androidx.navigation.toRoute


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
        startDestination = Screen.SearchScreen
    ) {
        composable<Screen.SearchScreen>(
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) {
//            Log.d("MyTag","${it.destination.route}\n${it.destination.id}\n${it.destination.navigatorName}\n${it.destination.label}")
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
        composable<Screen.VacancyScreen>(
            enterTransition = { customEnterTransition },
            exitTransition = { customExitTransition },
            popEnterTransition = { customEnterTransition },
            popExitTransition = { customExitTransition },
        ) { entry ->
            val args = entry.toRoute<Screen.VacancyScreen>()
            val vacancyViewModel = viewModel<VacancyViewModel>(
                factory = VacancyViewModel.Factory
            )
            val state by vacancyViewModel.uiState.collectAsStateWithLifecycle()
            vacancyViewModel.onEvent(
                VacancyEvent.SetVacancy(
                    VacancyModel(
                        id = args.id,
                        position = args.position,
                        salary = args.salary,
                        company = CompanyModel(
                            args.companyId,
                            args.companyName
                        ),
                        edArea = args.edArea,
                        formOfEmployment = args.formOfEmployment,
                        requirements = args.requirements,
                        location = args.location,
                        about = args.about,
                        liked = args.liked
                    )
                )
            )
            VacancyScreen(
                navController = navController,
                state = state,
                onEvent = vacancyViewModel::onEvent
            )
        }

        composable<Screen.FavouritesScreen>(
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
        navigation<Screen.Messenger>(startDestination = Screen.ChatListScreen) {
            composable<Screen.ChatListScreen>(
                enterTransition = { customEnterTransition },
                exitTransition = { customExitTransition },
                popEnterTransition = { customEnterTransition },
                popExitTransition = { customExitTransition },
            ) { entry ->
                val messengerViewModel = entry.sharedViewModel<MessengerViewModel>(
                    factory = MessengerViewModel.Factory,
                    navController = navController
                )
                val state by messengerViewModel.uiState.collectAsStateWithLifecycle()

                ChatListScreen(
                    navController = navController,
                    onEvent = messengerViewModel::onEvent,
                    state = state
                )
            }

            composable<Screen.MessengerScreen>(
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
                val messengerViewModel = entry.sharedViewModel<MessengerViewModel>(
                    factory = MessengerViewModel.Factory,
                    navController = navController
                )
                val state by messengerViewModel.uiState.collectAsStateWithLifecycle()
                MessengerScreen(
                    navController = navController,
                    onEvent = messengerViewModel::onEvent,
                    state = state
                )
            }
        }

        composable<Screen.ResponsesListScreen>(
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

        navigation<Screen.Profile>(startDestination = Screen.ProfileScreen) {
            composable<Screen.ProfileScreen>(
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

            composable<Screen.ProfileRedactorScreen>(
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
        }

        navigation<Screen.Resume>(startDestination = Screen.ResumeScreen,) {
            composable<Screen.ResumeScreen>(
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
            composable<Screen.ResumeRedactorScreen>(
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
}

