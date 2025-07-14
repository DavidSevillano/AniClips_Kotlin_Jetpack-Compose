package com.burixer85.aniclips.view.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.burixer85.aniclips.view.auth.activateAccount.ActivateAccountScreen
import com.burixer85.aniclips.view.auth.login.LoginScreen
import com.burixer85.aniclips.view.auth.register.RegisterScreen
import com.burixer85.aniclips.view.main.MainScreen

@Composable
fun NavigationWrapper(onDestinationChanged: (Boolean) -> Unit) {
    val navController = rememberNavController()
    val navBackStackEntry = navController.currentBackStackEntryAsState().value
    val isMainScreen = navBackStackEntry?.destination?.route == MainScreen::class.qualifiedName

    SideEffect {
        onDestinationChanged(isMainScreen)
    }

    NavHost(navController = navController, startDestination = LoginScreen) {
        composable<LoginScreen>(
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(400)
                )
            }
        ) {
            LoginScreen(
                navigateToRegister = { navController.navigate(RegisterScreen) },
                navigateToMain = {
                    navController.navigate(MainScreen)
                }
            )
        }

        composable<RegisterScreen>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(400)
                )
            }
        ) {

            RegisterScreen(
                navigateToActivateAccount = {
                    navController.navigate(
                        ActivateAccountScreen
                    )
                },
                navigateBack = { navController.popBackStack() })
        }

        composable<ActivateAccountScreen>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(400)
                )
            }
        ) {
            ActivateAccountScreen(navigateToLogin = { navController.navigate(LoginScreen) })
        }

        composable<MainScreen>(
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    tween(400)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    tween(400)
                )
            }
        ) {
            MainScreen()
        }
    }
}