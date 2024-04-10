package com.traxion.traxmisor.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.traxion.home.navigation.homeRoute
import com.traxion.home.navigation.homeScreen
import com.traxion.login.navigation.loginRoute
import com.traxion.login.navigation.loginScreen
import com.traxion.traxmisor.ui.AppState

@Composable
fun AppNavHost(
    appState: AppState,
    modifier: Modifier = Modifier,
    startDestination: String = loginRoute
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen()
        loginScreen()
    }
}