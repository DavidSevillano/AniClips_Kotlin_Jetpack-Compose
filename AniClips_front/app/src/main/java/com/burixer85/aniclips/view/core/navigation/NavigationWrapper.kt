package com.burixer85.aniclips.view.core.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.burixer85.aniclips.view.auth.activateAccount.ActivateAccountScreen
import com.burixer85.aniclips.view.auth.login.LoginScreen
import com.burixer85.aniclips.view.auth.register.RegisterScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()

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
            LoginScreen(navigateToRegister = { navController.navigate(RegisterScreen) })
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
            },
        ) {
            ActivateAccountScreen()
        }
    }
}