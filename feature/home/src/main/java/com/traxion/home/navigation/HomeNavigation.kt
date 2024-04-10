package com.traxion.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.traxion.home.HomeRoute

const val homeRoute = "home_route"

fun NavGraphBuilder.homeScreen() {
    composable(route = homeRoute) {
        HomeRoute()
    }
}