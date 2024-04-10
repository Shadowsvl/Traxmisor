package com.traxion.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.traxion.login.LoginRoute

const val loginRoute = "login_route"

fun NavGraphBuilder.loginScreen() {
    composable(route = loginRoute) {
        LoginRoute()
    }
}