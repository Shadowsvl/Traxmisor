package com.traxion.traxmisor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import com.traxion.home.navigation.homeRoute
import com.traxion.home.navigation.homeScreen
import com.traxion.home.navigation.navigateToHome
import com.traxion.login.navigation.loginRoute
import com.traxion.login.navigation.loginScreen
import com.traxion.login.navigation.navigateToLogin
import com.traxion.traxmisor.ui.AppState

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String
) {
    val navController = appState.navController

    /*
    * Host for Android Compose Navigation library.
    * Handles the function to perform navigation actions declared in feature modules.
    * */
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onNavigateToLogin = {
                navController.navigateToLogin(
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(homeRoute, inclusive = true)
                        .setLaunchSingleTop(true)
                        .build()
                )
            }
        )
        loginScreen(
            onNavigateToHome = {
                navController.navigateToHome(
                    navOptions = NavOptions.Builder()
                        .setPopUpTo(loginRoute, inclusive = true)
                        .setLaunchSingleTop(true)
                        .build()
                )
            }
        )
    }
}