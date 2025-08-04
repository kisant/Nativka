package com.kis.nativka.biometric.presentation

import android.util.Log
import androidx.activity.compose.LocalActivity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.fragment.app.FragmentActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

private const val TAG = "BiometricRoute--->"

@Composable
internal fun BiometricRoute(
    modifier: Modifier = Modifier,
    viewModel: BiometricViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val fragmentActivity = LocalActivity.current as? FragmentActivity ?: run {
        Log.e(TAG, "Current activity is not a FragmentActivity. Cannot authenticate.")
        return
    }

    BiometricScreen(
        modifier = modifier,
        uiState = uiState,
        onAuthenticate = { viewModel.onEvent(BiometricScreenUiEvent.OnAuthenticateClick, fragmentActivity) }
    )
}