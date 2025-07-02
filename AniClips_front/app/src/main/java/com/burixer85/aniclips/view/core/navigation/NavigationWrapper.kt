package com.burixer85.aniclips.view.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.burixer85.aniclips.view.auth.register.RegisterScreen

@Composable
fun NavigationWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Register) {
        composable(Screens.Register) {
            RegisterScreen()
        }
    }
}