package com.kis.nativka.biometric.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun BiometricRoute(
    modifier: Modifier = Modifier,
    viewModel: BiometricViewModel = hiltViewModel()
) {
    BiometricScreen(
        modifier = modifier
    )
}