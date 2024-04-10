package com.traxion.login

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
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
import com.arch.ui.R
import com.arch.ui.component.EmailInput
import com.arch.ui.component.PasswordInput

@Composable
internal fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val email by viewModel.email.collectAsStateWithLifecycle()
    val password by viewModel.password.collectAsStateWithLifecycle()

    LoginScreen(
        uiState = uiState,
        email = email,
        onEmailChanged = viewModel::onEmailChanged,
        password = password,
        onPasswordChanged = viewModel::onPasswordChanged
    )
}

@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    email: String,
    onEmailChanged: (String) -> Unit,
    password: String,
    onPasswordChanged: (String) -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            verticalArrangement = Arrangement.spacedBy(basePadding, Alignment.CenterVertically),
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
                style = MaterialTheme.typography.titleLarge
            )
            when(uiState) {
                is LoginUiState.AuthError -> TODO()
                LoginUiState.Loading -> TODO()
                LoginUiState.LogIn -> {
                    EmailInput(
                        email = email,
                        onEmailChanged = onEmailChanged,
                        modifier = Modifier.fillMaxWidth()
                    )
                    PasswordInput(
                        password = password,
                        onPasswordChanged = onPasswordChanged,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = { /*TODO function to log in*/ },
                        enabled = email.isNotBlank() && password.isNotBlank(),
                        modifier = Modifier.fillMaxWidth().padding(top = basePadding)
                    ) {
                        Text(text = stringResource(id = R.string.btn_log_in))
                    }
                }
                LoginUiState.Logged -> TODO()
            }
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
                password = "",
                onPasswordChanged = {}
            )
        }
    }
}