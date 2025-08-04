package com.kis.nativka.biometric.data.repository

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.kis.nativka.biometric.domain.repository.BiometricRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BiometricRepositoryImpl @Inject constructor() : BiometricRepository {
    override suspend fun authenticateWithBiometrics(
        context: Context
    ): Boolean = suspendCoroutine { continuation ->
        val executor = ContextCompat.getMainExecutor(context)

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Confirm your identity")
            .setNegativeButtonText("Cancel")
            .build()

        val biometricPrompt = BiometricPrompt(
            context as FragmentActivity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    continuation.resume(true)
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    continuation.resume(false)
                }

                override fun onAuthenticationFailed() {
                    continuation.resume(false)
                }
            }
        )
        biometricPrompt.authenticate(promptInfo)
    }
}