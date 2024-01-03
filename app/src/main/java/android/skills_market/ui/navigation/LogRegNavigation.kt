package android.skills_market.ui.navigation

import android.skills_market.ui.screens.CityCourseAndPhone
import android.skills_market.ui.screens.EmailAndPasswordScreen
import android.skills_market.ui.screens.LogRegScreen
import android.skills_market.ui.screens.LoginScreen
import android.skills_market.ui.screens.NameAndGenderRegScreen
import android.skills_market.ui.screens.RegistrationScreen
import android.skills_market.view_models.RegViewModel
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Ease
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.EaseOutSine
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
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
            LoginScreen(navController = navController)
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
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
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

