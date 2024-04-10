package com.traxion.traxmisor.ui

import androidx.compose.runtime.Composable
import com.arch.design_system.component.AppBackground
import com.traxion.traxmisor.navigation.AppNavHost

@Composable
fun TraxmisorApp(
    appState: AppState = rememberAppState()
) {
    AppBackground {
        AppNavHost(
            appState = appState
        )
    }
}