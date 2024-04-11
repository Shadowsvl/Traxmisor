package com.traxion.home

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data object Success : HomeUiState
}