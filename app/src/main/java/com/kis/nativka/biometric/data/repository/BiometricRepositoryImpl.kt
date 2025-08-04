package com.kis.nativka.biometric.data.repository

import android.util.Log
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.kis.nativka.R
import com.kis.nativka.biometric.domain.repository.BiometricRepository
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class BiometricRepositoryImpl @Inject constructor() : BiometricRepository {

    companion object {
        private const val TAG = "BiometricRepositoryImpl--->"
    }

    override suspend fun authenticateWithBiometrics(
        activity: FragmentActivity
    ): Boolean = suspendCoroutine { continuation ->
        Log.d(TAG, "authenticateWithBiometrics called with activity: ${activity.javaClass.simpleName}")
        val executor = ContextCompat.getMainExecutor(activity)

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(activity.getString(R.string.biometric_title))
            .setSubtitle(activity.getString(R.string.biometric_subtitle))
            .setNegativeButtonText(activity.getString(R.string.biometric_cancel))
            .build()

        var isResumed = false

        val biometricPrompt = BiometricPrompt(
            activity,
            executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                    if (!isResumed) {
                        isResumed = true
                        Log.d(TAG, "Authentication succeeded")
                        continuation.resume(true)
                    } else {
                        Log.w(TAG, "Coroutine already resumed: success")
                    }
                }

                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    if (!isResumed) {
                        isResumed = true
                        Log.e(TAG, "Authentication error: $errorCode, $errString")
                        continuation.resume(false)
                    } else {
                        Log.w(TAG, "Coroutine already resumed: error")
                    }
                }

                override fun onAuthenticationFailed() {
                    Log.i(TAG, "Authentication failed")
                }
            }
        )
        biometricPrompt.authenticate(promptInfo)
    }
}