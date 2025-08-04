package com.kis.nativka.biometric.domain.usecase

import android.content.Context
import com.kis.nativka.biometric.domain.repository.BiometricRepository
import javax.inject.Inject

class CheckBiometricAvailabilityUseCase @Inject constructor(
    private val biometricRepository: BiometricRepository
) {
    suspend operator fun invoke(context: Context): Boolean {
        return biometricRepository.authenticateWithBiometrics(context)
    }
}