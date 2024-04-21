package android.skills_market.ui.navigation

import android.skills_market.ui.screens.authentication.LogRegScreen
import android.skills_market.ui.screens.authentication.LoginScreen
import android.skills_market.ui.screens.authentication.registration_screen.RegistrationScreen
import android.skills_market.ui.screens.authentication.registration_screen.CityCourseAndPhoneScreen
import android.skills_market.ui.screens.authentication.registration_screen.EmailAndPasswordScreen
import android.skills_market.ui.screens.authentication.registration_screen.NameAndGenderScreen
import android.skills_market.view_model.LoginViewModel
import android.skills_market.view_model.RegUIState
import android.skills_market.view_model.RegViewModel
import android.skills_market.view_model.event.RegistrationEvent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun LogRegNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.LogRegScreen.route,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {
        composable(
            route = Screen.LogRegScreen.route,
            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            enterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            }
        ) {
            LogRegScreen(navController = navController)
        }
        composable(
            route = Screen.LoginScreen.route,
            enterTransition = {
                slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            val loginViewModel = viewModel<LoginViewModel>(
                factory = LoginViewModel.Factory
            )
            val state by loginViewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                navController = navController,
                onEvent = loginViewModel::onEvent,
                state = state
            )
        }
        composable(
            route = Screen.RegistrationScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) { entry ->
            val regViewModel = viewModel<RegViewModel>(
                factory = RegViewModel.Factory
            )
            val state by regViewModel.uiState.collectAsStateWithLifecycle()
            RegistrationScreen(
                navController = navController,
                onEvent = regViewModel::onEvent,
                state = state
            )
        }
    }
}

@Composable
fun RegGraph(
    navController: NavHostController,
    onEvent: (RegistrationEvent) -> Unit,
    state: RegUIState.Success
) {
    NavHost(
        navController = navController,
        startDestination = Screen.EmailAndPasswordScreen.route,
    ) {
        composable(
            route = Screen.EmailAndPasswordScreen.route,
            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
        ) {
            EmailAndPasswordScreen(
                navController = navController,
                onEvent = onEvent,
                uiState = state
            )
        }
        composable(
            route = Screen.NameAndGenderScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            NameAndGenderScreen(
                navController = navController,
                onEvent = onEvent,
                uiState = state
            )
        }
        composable(
            route = Screen.CityCourseAndPhoneScreen.route,
            enterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            exitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.Start
                )
            },
            popEnterTransition = {
                fadeIn(
                    animationSpec = tween(250, easing = LinearEasing),
                ) + slideIntoContainer(
                    animationSpec = tween(250, easing = EaseIn),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            },
            popExitTransition = {
                fadeOut(
                    animationSpec = tween(
                        250, easing = LinearEasing
                    )
                ) + slideOutOfContainer(
                    animationSpec = tween(250, easing = EaseOut),
                    towards = AnimatedContentTransitionScope.SlideDirection.End
                )
            }
        ) {
            CityCourseAndPhoneScreen(
                navController = navController,
                onEvent = onEvent,
                uiState = state
            )
        }
    }
}




