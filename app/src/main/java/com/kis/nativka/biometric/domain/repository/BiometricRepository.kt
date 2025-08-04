package com.kis.nativka.biometric.domain.repository

import android.content.Context

interface BiometricRepository {
    suspend fun authenticateWithBiometrics(context: Context): Boolean
}