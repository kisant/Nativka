package com.kis.nativka.biometric.domain.repository

import androidx.fragment.app.FragmentActivity

interface BiometricRepository {
    suspend fun authenticateWithBiometrics(activity: FragmentActivity): Boolean
}