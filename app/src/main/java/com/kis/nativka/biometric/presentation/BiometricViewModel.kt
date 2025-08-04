package com.kis.nativka.biometric.presentation

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kis.nativka.biometric.domain.usecase.BiometricAuthenticationUseCase
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
    private val authenticateUser: BiometricAuthenticationUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<BiometricScreenUiState>(BiometricScreenUiState.ReadyToAuthenticate)
    val uiState: StateFlow<BiometricScreenUiState> = _uiState.asStateFlow()

    fun onEvent(event: BiometricScreenUiEvent, activity: FragmentActivity) {
        when (event) {
            is OnAuthenticateClick -> authenticateWithBiometrics(activity)
        }
    }

    fun authenticateWithBiometrics(activity: FragmentActivity) = viewModelScope.launch {
        val isAuthenticated = authenticateUser(activity)
        _uiState.value = BiometricScreenUiState.AuthenticationResult(isAuthenticated)
    }
}