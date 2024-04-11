package com.traxion.traxmisor.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.arch.data.repository.PreferenceRepository
import com.arch.data.util.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberAppState(
    networkMonitor: NetworkMonitor,
    preferenceRepository: PreferenceRepository,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    navController: NavHostController = rememberNavController()
): AppState {
    return remember(
        networkMonitor,
        preferenceRepository,
        coroutineScope,
        navController
    ) {
        AppState(
            networkMonitor = networkMonitor,
            preferenceRepository = preferenceRepository,
            navController = navController,
            coroutineScope = coroutineScope
        )
    }
}

@Stable
class AppState(
    networkMonitor: NetworkMonitor,
    preferenceRepository: PreferenceRepository,
    val navController: NavHostController,
    val coroutineScope: CoroutineScope
) {

    val isOffline = networkMonitor.isOnline
        .map(Boolean::not)
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )

    val isLogged = preferenceRepository.isLogged()
        .stateIn(
            scope = coroutineScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = false
        )
}