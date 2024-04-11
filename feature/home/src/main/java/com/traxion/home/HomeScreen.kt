package com.traxion.home

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppBackground
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.extraLargePadding
import com.arch.ui.R
import com.arch.ui.component.Profile
import com.arch.ui.fakeUser

@Composable
internal fun HomeRoute(
    onLogOut: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        onLogOutClick = {
            viewModel.logOut()
            onLogOut()
        }
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    onLogOutClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(extraLargePadding),
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .animateContentSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(basePadding),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = basePadding)
                .padding(top = basePadding)
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_transmission),
                contentDescription = "Transmission Icon",
                colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
                modifier = Modifier.size(35.dp)
            )
            Text(
                text = stringResource(id = R.string.app_name).uppercase(),
                style = MaterialTheme.typography.titleLarge
            )
            Box(
                contentAlignment = Alignment.CenterEnd,
                modifier = Modifier.weight(0.4f, fill = true)
            ) {
                Button(
                    onClick = onLogOutClick,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.errorContainer,
                        contentColor = MaterialTheme.colorScheme.onErrorContainer
                    )
                ) {
                    Text(text = stringResource(id = R.string.btn_log_out))
                }
            }
        }
        when(uiState) {
            HomeUiState.Loading -> {
                CircularProgressIndicator()
            }
            is HomeUiState.Success -> {
                Profile(
                    user = uiState.user,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = basePadding)
                )
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
                uiState = HomeUiState.Success(
                    user = fakeUser
                ),
                onLogOutClick = {}
            )
        }
    }
}