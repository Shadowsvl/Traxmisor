package com.traxion.traxmisor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arch.data.repository.PreferenceRepository
import com.arch.data.util.NetworkMonitor
import com.arch.design_system.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.traxion.traxmisor.ui.TraxmisorApp
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var preferenceRepository: PreferenceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()

            DisposableEffect(systemUiController) {
                systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = false)
                onDispose {}
            }

            AppTheme {
                TraxmisorApp(
                    networkMonitor = networkMonitor,
                    preferenceRepository = preferenceRepository
                )
            }
        }
    }
}