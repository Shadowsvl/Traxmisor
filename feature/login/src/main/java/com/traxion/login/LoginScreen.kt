package com.traxion.login

import android.Manifest
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
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
import com.arch.design_system.theme.largePadding
import com.arch.ui.R
import com.arch.ui.component.EmailInput
import com.arch.ui.component.NotifyCard
import com.arch.ui.component.PasswordInput
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@Composable
internal fun LoginRoute(
    onLogInSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val emailError by viewModel.emailError.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()
    val passwordError by viewModel.passwordError.collectAsStateWithLifecycle()
    val keyboardController = LocalSoftwareKeyboardController.current

    LoginScreen(
        uiState = uiState,
        email = email,
        onEmailChanged = viewModel::onEmailChanged,
        emailError = emailError,
        password = password,
        onPasswordChanged = viewModel::onPasswordChanged,
        passwordError = passwordError,
        onLogInClick = {
            keyboardController?.hide()
            viewModel.logIn()
        },
        onContinueLogInClick = viewModel::continueLogIn,
        onLogInSuccess = onLogInSuccess
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    email: String,
    onEmailChanged: (String) -> Unit,
    emailError: Boolean,
    password: String,
    onPasswordChanged: (String) -> Unit,
    passwordError: Boolean,
    onLogInClick: () -> Unit,
    onContinueLogInClick: () -> Unit,
    onLogInSuccess: () -> Unit
) {

    val permissions = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    )

    Column(
        verticalArrangement = Arrangement.spacedBy(largePadding, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(basePadding)
            .animateContentSize()
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_transmission),
            contentDescription = "Transmission Icon",
            colorFilter = ColorFilter.tint(color = MaterialTheme.colorScheme.onBackground),
            modifier = Modifier.size(60.dp)
        )
        Text(
            text = stringResource(id = R.string.app_name).uppercase(),
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        if (permissions.allPermissionsGranted) {
            when(uiState) {
                is LoginUiState.AuthError -> {
                    NotifyCard(
                        message = stringResource(id = R.string.notify_log_in_error),
                        actionText = stringResource(id = R.string.btn_accept),
                        onActionClick = onContinueLogInClick
                    )
                }
                LoginUiState.Loading -> {
                    CircularProgressIndicator()
                }
                LoginUiState.LogIn -> {
                    EmailInput(
                        email = email,
                        onEmailChanged = onEmailChanged,
                        isError = emailError,
                        errorMessage = stringResource(id = R.string.notify_email_not_valid),
                        modifier = Modifier.fillMaxWidth()
                    )
                    PasswordInput(
                        password = password,
                        onPasswordChanged = onPasswordChanged,
                        isError = passwordError,
                        errorMessage = stringResource(id = R.string.notify_password_size_not_valid),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = onLogInClick,
                        enabled = email.isNotBlank() && password.isNotBlank(),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = basePadding)
                    ) {
                        Text(text = stringResource(id = R.string.btn_log_in))
                    }
                }
                LoginUiState.Logged -> {
                    onLogInSuccess()
                }
            }
        } else {
            NotifyCard(
                message = stringResource(id = R.string.message_location_permission),
                onActionClick = { permissions.launchMultiplePermissionRequest() }
            )
        }
    }
}

@ThemePreviews
@Composable
private fun LoginScreenPreview() {
    AppTheme {
        AppBackground {
            LoginScreen(
                uiState = LoginUiState.LogIn,
                email = "",
                onEmailChanged = {},
                emailError = false,
                password = "",
                onPasswordChanged = {},
                passwordError = false,
                onLogInClick = {},
                onContinueLogInClick = {},
                onLogInSuccess = {}
            )
        }
    }
}