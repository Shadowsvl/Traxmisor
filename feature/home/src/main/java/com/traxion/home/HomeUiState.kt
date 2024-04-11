package com.traxion.home

import com.traxion.model.data.User

sealed interface HomeUiState {

    data object Loading : HomeUiState

    data class Success(
        val user: User
    ) : HomeUiState
}