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
import androidx.compose.ui.unit.dp

@Composable
fun BiometricScreen(
    modifier: Modifier = Modifier,
    onAuthenticate: () -> Unit,
    authResult: Boolean?
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = onAuthenticate) {
            Text("Enter with biometry")
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (authResult) {
            true -> Text("Authentication success", color = MaterialTheme.colorScheme.primary)
            false -> Text("Authentication error", color = MaterialTheme.colorScheme.error)
            null -> {}
        }
    }
}