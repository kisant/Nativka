package com.kis.nativka.biometric.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun BiometricRoute(
    modifier: Modifier = Modifier,
    viewModel: BiometricViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    BiometricScreen(
        modifier = modifier,
        uiState = uiState,
        onAuthenticate = { viewModel.onEvent(BiometricScreenUiEvent.OnAuthenticateClick, context) }
    )
}