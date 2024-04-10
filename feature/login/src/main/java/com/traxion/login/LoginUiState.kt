package com.traxion.login

sealed interface LoginUiState {

    data object LogIn : LoginUiState

    data object Loading : LoginUiState

    data class AuthError(val message: String) : LoginUiState

    data object Logged : LoginUiState
}