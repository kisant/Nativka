package com.kis.nativka.biometric.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.kis.nativka.R
import com.kis.nativka.ui.theme.Dimens

@Composable
internal fun BiometricScreen(
    modifier: Modifier = Modifier,
    uiState: BiometricScreenUiState,
    onAuthenticate: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimens.ScreenPadding),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (uiState) {
            is BiometricScreenUiState.ReadyToAuthenticate -> {
                ReadyToAuthenticateContent(onAuthenticate = onAuthenticate)
            }
            is BiometricScreenUiState.AuthenticationResult -> {
                AuthenticationResultContent(isAuthenticated = uiState.isAuthenticated)
            }
        }
    }
}

@Composable
private fun ReadyToAuthenticateContent(onAuthenticate: () -> Unit) {
    Text(
        text = stringResource(R.string.biometric_title),
        style = MaterialTheme.typography.titleMedium
    )
    Spacer(modifier = Modifier.height(Dimens.ItemSpacingMedium))
    Button(onClick = onAuthenticate) {
        Text(stringResource(R.string.biometric_authenticate_button))
    }
}

@Composable
private fun AuthenticationResultContent(isAuthenticated: Boolean) {
    val messageId = if (isAuthenticated) {
        R.string.biometric_auth_success
    } else {
        R.string.biometric_auth_error
    }
    Text(
        text = stringResource(id = messageId),
        style = MaterialTheme.typography.titleMedium
    )
}