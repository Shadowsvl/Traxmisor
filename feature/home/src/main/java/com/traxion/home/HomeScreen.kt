package com.traxion.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppBackground
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.ui.R

@Composable
internal fun HomeRoute(
    onLogOut: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onLogOut = {
            viewModel.logOut()
            onLogOut()
        }
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onLogOut: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = uiState == HomeUiState.Success,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = "Traxmisor".uppercase(),
                style = MaterialTheme.typography.titleLarge
            )
        }
        AnimatedVisibility(
            visible = uiState == HomeUiState.Success,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(basePadding)
        ) {
            Button(onClick = onLogOut) {
                Text(text = stringResource(id = R.string.btn_log_out))
            }
        }
    }
}

@ThemePreviews
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        AppBackground {
            HomeScreen(
                uiState = HomeUiState.Success,
                onLogOut = {}
            )
        }
    }
}