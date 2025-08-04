package com.kis.nativka.biometric.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kis.nativka.biometric.domain.usecase.CheckBiometricAvailabilityUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BiometricViewModel @Inject constructor(
    private val checkBiometricUseCase: CheckBiometricAvailabilityUseCase
) : ViewModel() {

    fun authenticateWithBiometrics(
        context: Context, onResult: (Boolean) -> Unit
    ) = viewModelScope.launch {
        onResult(checkBiometricUseCase(context))
    }
}