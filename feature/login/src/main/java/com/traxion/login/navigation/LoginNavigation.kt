package com.traxion.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.traxion.login.LoginRoute

const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen(
    onNavigateToHome: () -> Unit
) {
    composable(route = loginRoute) {
        LoginRoute(
            onLogInSuccess = onNavigateToHome
        )
    }
}