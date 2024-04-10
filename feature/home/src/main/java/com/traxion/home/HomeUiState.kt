package com.traxion.home

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class AuthError(val message: String) : HomeUiState

    data object Success : HomeUiState
}