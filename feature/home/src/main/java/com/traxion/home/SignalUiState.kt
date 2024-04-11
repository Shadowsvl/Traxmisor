package com.traxion.home

sealed interface SignalUiState {

    data object WaitingSignal: SignalUiState

    data object SendingSignal: SignalUiState

    data object SignalSent: SignalUiState

    data object SignalFailed: SignalUiState
}