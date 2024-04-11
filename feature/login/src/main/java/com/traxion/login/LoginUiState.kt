package com.traxion.login

sealed interface LoginUiState {

    data object LogIn : LoginUiState

    data object Loading : LoginUiState

    data object AuthError : LoginUiState

    data object Logged : LoginUiState
}