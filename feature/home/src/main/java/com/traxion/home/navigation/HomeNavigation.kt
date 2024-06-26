package com.traxion.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.traxion.home.HomeRoute

const val homeRoute = "home_route"

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(homeRoute, navOptions)
}

fun NavGraphBuilder.homeScreen(
    onNavigateToLogin: () -> Unit
) {
    composable(route = homeRoute) {
        HomeRoute(
            onLogOut = onNavigateToLogin
        )
    }
}