package com.traxion.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arch.common.result.Result
import com.arch.data.domain.EmailValidationUseCase
import com.arch.data.domain.LogInUseCase
import com.arch.data.repository.PreferenceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val emailValidationUseCase: EmailValidationUseCase,
    private val logInUseCase: LogInUseCase,
    private val preferenceRepository: PreferenceRepository
) : ViewModel() {

    private var _uiState: MutableStateFlow<LoginUiState> = MutableStateFlow(LoginUiState.Loading)

    val uiState = _uiState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _uiState.value
    )

    private var _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private var _emailError = MutableStateFlow(false)
    val emailError = _emailError.asStateFlow()

    private var _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private var _passwordError = MutableStateFlow(false)
    val passwordError = _passwordError.asStateFlow()

    fun onEmailChanged(value: String) = _email.update { value }

    fun onPasswordChanged(value: String) = _password.update { value }

    init {
        viewModelScope.launch {
            preferenceRepository.isLogged().collectLatest {  isLogged ->
                if (!isLogged) _uiState.update { LoginUiState.LogIn }
            }
        }
    }

    fun logIn() {
        val isEmailValid = emailValidationUseCase(_email.value)
        val isPasswordValid = _password.value.length >= 6

        _emailError.update { !isEmailValid }
        _passwordError.update { !isPasswordValid }

        if (isEmailValid && isPasswordValid) {
            _uiState.update { LoginUiState.Loading }
            viewModelScope.launch {
                when(logInUseCase(_email.value, _password.value)) {
                    is Result.Error -> _uiState.update { LoginUiState.AuthError }
                    is Result.Success -> {
                        preferenceRepository.setIsLogged(true)
                        _uiState.update { LoginUiState.Logged }
                    }
                }
            }
        }
    }

    fun continueLogIn() {
        _uiState.update { LoginUiState.LogIn }
    }
}