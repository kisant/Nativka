package com.kis.nativka.biometric.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun BiometricRoute(
    modifier: Modifier = Modifier,
    viewModel: BiometricViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    var authResult by remember { mutableStateOf<Boolean?>(null) }

    BiometricScreen(
        modifier = modifier,
        onAuthenticate = {
            viewModel.authenticateWithBiometrics(context) {
                authResult = it
            }
        },
        authResult = authResult
    )
}