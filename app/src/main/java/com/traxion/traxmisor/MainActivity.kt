package com.traxion.traxmisor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.arch.design_system.theme.AppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.traxion.traxmisor.ui.TraxmisorApp
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

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
                TraxmisorApp()
            }
        }
    }
}