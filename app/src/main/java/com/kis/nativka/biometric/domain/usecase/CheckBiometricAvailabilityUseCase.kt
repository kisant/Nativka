package com.kis.nativka.biometric.domain.usecase

import androidx.fragment.app.FragmentActivity
import com.kis.nativka.biometric.data.repository.BiometricRepositoryImpl
import com.kis.nativka.biometric.domain.repository.BiometricRepository
import javax.inject.Inject

class CheckBiometricAvailabilityUseCase @Inject constructor(
    private val biometricRepository: BiometricRepository
) {
    suspend operator fun invoke(activity: FragmentActivity): Boolean {
        return biometricRepository.authenticateWithBiometrics(activity)
    }
}