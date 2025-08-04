package com.kis.nativka.biometric.domain.repository

import android.content.Context

interface BiometricRepository {
    // May cause memory leaks
    suspend fun authenticateWithBiometrics(context: Context): Boolean
}