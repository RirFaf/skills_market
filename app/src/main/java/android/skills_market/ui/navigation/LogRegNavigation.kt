package android.skills_market.ui.navigation

import android.skills_market.ui.screens.CityCourseAndPhone
import android.skills_market.ui.screens.EmailAndPasswordScreen
import android.skills_market.ui.screens.LogRegScreen
import android.skills_market.ui.screens.LoginScreen
import android.skills_market.ui.screens.NameAndGenderRegScreen
import android.skills_market.ui.screens.RegistrationScreen
import android.skills_market.view_model.LoginViewModel
import android.skills_market.view_model.RegViewModel
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

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
            exitTransition = {
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
            val uiState by loginViewModel.uiState.collectAsStateWithLifecycle()
            LoginScreen(
                navController = navController,
                onEvent = loginViewModel::onEvent,
                uiState = uiState
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
        ){
            RegistrationScreen(navController = navController)
        }
    }
}

@Composable
fun RegGraph(
    navController: NavHostController,
    viewModel: RegViewModel
) {
    NavHost(
        navController = navController,
        startDestination = Screen.NameAndGenderRegScreen.route,
    ) {
        composable(
            route = Screen.NameAndGenderRegScreen.route,
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
            NameAndGenderRegScreen(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screen.CityCourseAndPhone.route,
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
            CityCourseAndPhone(viewModel = viewModel, navController = navController)
        }
        composable(
            route = Screen.EmailAndPasswordScreen.route,
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
            EmailAndPasswordScreen(viewModel = viewModel)
        }
    }
}


