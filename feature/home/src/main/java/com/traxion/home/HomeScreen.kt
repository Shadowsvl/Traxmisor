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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.arch.design_system.annotations.ThemePreviews
import com.arch.design_system.component.AppBackground
import com.arch.design_system.theme.AppTheme
import com.arch.design_system.theme.basePadding
import com.arch.design_system.theme.extraLargePadding
import com.arch.ui.R
import com.arch.ui.component.NotifyCard
import com.arch.ui.component.Profile
import com.arch.ui.component.SosSignals
import com.arch.ui.fakeUser

@Composable
internal fun HomeRoute(
    onLogOut: () -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val signalState by viewModel.signalState.collectAsStateWithLifecycle()

    HomeScreen(
        uiState = uiState,
        signalState = signalState,
        onSendSignal = viewModel::sendSignal,
        onAcceptSignalResult = viewModel::acceptSignalResult,
        onLogOutClick = {
            viewModel.logOut()
            onLogOut()
        }
    )
}

@Composable
internal fun HomeScreen(
    uiState: HomeUiState,
    signalState: SignalUiState,
    onSendSignal: (String) -> Unit,
    onAcceptSignalResult: () -> Unit,
    onLogOutClick: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(extraLargePadding),
        modifier = Modifier
            .fillMaxSize()
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
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
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
                when(signalState) {
                    SignalUiState.WaitingSignal -> {
                        SosSignals(
                            onVehicleMalfunctionSignal = onSendSignal,
                            onAccidentSignal = onSendSignal,
                            onFlatTiresSignal = onSendSignal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = basePadding)
                        )
                    }
                    SignalUiState.SendingSignal -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                    }
                    SignalUiState.SignalFailed -> {
                        NotifyCard(
                            message = stringResource(id = R.string.message_sos_signal_failed),
                            actionText = stringResource(id = R.string.btn_accept),
                            onActionClick = onAcceptSignalResult,
                            modifier = Modifier.padding(horizontal = basePadding)
                        )
                    }
                    SignalUiState.SignalSent -> {
                        NotifyCard(
                            message = stringResource(id = R.string.message_sos_signal_sent),
                            actionText = stringResource(id = R.string.btn_accept),
                            onActionClick = onAcceptSignalResult,
                            modifier = Modifier.padding(horizontal = basePadding)
                        )
                    }
                }
            }
        }
        Box(
            contentAlignment = Alignment.BottomStart,
            modifier = Modifier
                .fillMaxSize()
                .weight(0.3f, fill = true)
                .padding(basePadding)
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
                signalState = SignalUiState.WaitingSignal,
                onSendSignal = {},
                onAcceptSignalResult = {},
                onLogOutClick = {}
            )
        }
    }
}