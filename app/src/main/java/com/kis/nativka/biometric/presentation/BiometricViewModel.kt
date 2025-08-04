package com.kis.nativka.biometric.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kis.nativka.biometric.domain.usecase.CheckBiometricAvailabilityUseCase
import com.kis.nativka.biometric.presentation.BiometricScreenUiEvent.OnAuthenticateClick
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

internal sealed interface BiometricScreenUiEvent {
    data object OnAuthenticateClick : BiometricScreenUiEvent
}

internal sealed interface BiometricScreenUiState {
    data object ReadyToAuthenticate : BiometricScreenUiState
    data class AuthenticationResult(val isAuthenticated: Boolean) : BiometricScreenUiState
}

@HiltViewModel
internal class BiometricViewModel @Inject constructor(
    private val checkBiometricUseCase: CheckBiometricAvailabilityUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BiometricScreenUiState>(BiometricScreenUiState.ReadyToAuthenticate)
    val uiState: StateFlow<BiometricScreenUiState> = _uiState.asStateFlow()

    fun onEvent(event: BiometricScreenUiEvent, context: Context) {
        when (event) {
            is OnAuthenticateClick -> authenticateWithBiometrics(context)
        }
    }

    fun authenticateWithBiometrics(context: Context) = viewModelScope.launch {
        val isAuthenticated = checkBiometricUseCase(context)
        _uiState.value = BiometricScreenUiState.AuthenticationResult(isAuthenticated)
    }
}