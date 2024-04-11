package com.traxion.traxmisor.ui

import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.data.repository.PreferenceRepository
import com.arch.data.util.NetworkMonitor
import com.arch.design_system.component.AppBackground
import com.arch.ui.R
import com.traxion.home.navigation.homeRoute
import com.traxion.login.navigation.loginRoute
import com.traxion.traxmisor.navigation.AppNavHost

@Composable
fun TraxmisorApp(
    networkMonitor: NetworkMonitor,
    preferenceRepository: PreferenceRepository,
    appState: AppState = rememberAppState(
        networkMonitor = networkMonitor,
        preferenceRepository = preferenceRepository
    )
) {
    AppBackground {
        val snackbarHostState = remember { SnackbarHostState() }

        val isOffline by appState.isOffline.collectAsStateWithLifecycle()
        val notConnectedMessage = stringResource(id = R.string.notify_not_connected)

        val isLogged by appState.isLogged.collectAsStateWithLifecycle()

        LaunchedEffect(isOffline) {
            if (isOffline) {
                snackbarHostState.showSnackbar(
                    message = notConnectedMessage,
                    duration = SnackbarDuration.Indefinite
                )
            }
        }

        Scaffold(
            containerColor = Color.Transparent,
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState
                ) { data ->
                    Snackbar(
                        containerColor = MaterialTheme.colorScheme.onBackground,
                        snackbarData = data
                    )
                }
            },
            modifier = Modifier.imePadding()
        ) { padding ->
            AppNavHost(
                appState = appState,
                modifier = Modifier.padding(padding),
                startDestination = if (isLogged) homeRoute else loginRoute
            )
        }
    }
}