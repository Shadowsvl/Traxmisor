package com.traxion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.data.location.LocationMonitor
import com.arch.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val locationMonitor: LocationMonitor
) : ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    init {
        viewModelScope.launch {
            delay(500)
            _uiState.update { HomeUiState.Success }
            locationMonitor.locationUpdates.collectLatest {
                // TODO: send Location sample updates
            }
        }
    }

    fun logOut() = viewModelScope.launch {
        preferenceRepository.setIsLogged(false)
    }
}