package com.traxion.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.common.result.getDataOrNull
import com.arch.data.location.LocationMonitor
import com.arch.data.repository.PreferenceRepository
import com.arch.data.repository.UserRepository
import com.traxion.model.mapper.asUserLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
    private val userRepository: UserRepository,
    private val locationMonitor: LocationMonitor
) : ViewModel() {

    private var _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    private lateinit var userId: String

    init {
        fetchUserData()
        viewModelScope.launch {
            locationMonitor.locationUpdates.collectLatest { locationSample ->
                if (userId.isNotBlank()) {
                    userRepository.sendUserLocation(locationSample.asUserLocation(userId))
                }
            }
        }
    }

    private fun fetchUserData() = viewModelScope.launch {
        val userResult = userRepository.getUser(preferenceRepository.userId().first())
        userResult.getDataOrNull()?.let { user ->
            userId = user.id
            _uiState.update { HomeUiState.Success(user) }
        }
    }

    fun logOut() = viewModelScope.launch {
        preferenceRepository.setIsLogged(false)
    }
}