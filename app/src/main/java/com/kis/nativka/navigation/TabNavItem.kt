package com.kis.nativka.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bluetooth
import androidx.compose.material.icons.outlined.Fingerprint
import androidx.compose.material.icons.outlined.Nfc
import androidx.compose.material.icons.outlined.PhonelinkLock
import androidx.compose.material.icons.outlined.Shield
import androidx.compose.ui.graphics.vector.ImageVector
import com.kis.nativka.R

sealed class TabNavItem(
    val route: String,
    val icon: ImageVector,
    @StringRes val label: Int,
    @StringRes val contentDescription: Int
) {
    object Biometric : TabNavItem(
        route = "biometric",
        icon = Icons.Outlined.Fingerprint,
        label = R.string.biometric_nav_label,
        contentDescription = R.string.biometric_nav_description
    )
    object TEE : TabNavItem(
        route = "tee",
        icon = Icons.Outlined.Shield,
        label = R.string.tee_nav_label,
        contentDescription = R.string.tee_nav_description
    )
    object NFC : TabNavItem(
        route = "nfc",
        icon = Icons.Outlined.Nfc,
        label = R.string.nfc_nav_label,
        contentDescription = R.string.nfc_nav_description
    )
    object BLE : TabNavItem(
        route = "ble",
        icon = Icons.Outlined.Bluetooth,
        label = R.string.ble_nav_label,
        contentDescription = R.string.ble_nav_description
    )
    object OMAPI : TabNavItem(
        route = "omapi",
        icon = Icons.Outlined.PhonelinkLock,
        label = R.string.omapi_nav_label,
        contentDescription = R.string.omapi_nav_description
    )

    companion object {
        val items by lazy { listOf(Biometric, TEE, NFC, BLE, OMAPI) }
        val routes by lazy { items.map { it.route } }
    }
}

//enum class TabNavItem(
//    val route: String,
//    val icon: ImageVector,
//    @StringRes val label: Int,
//    @StringRes val contentDescription: Int
//) {
//    Biometric(
//        "biometric",
//        Icons.Default.Face,
//        R.string.biometric_nav_label,
//        R.string.biometric_nav_description
//    ),
//    TEE(
//        "tee",
//        Icons.Default.Face,
//        R.string.biometric_nav_label,
//        R.string.biometric_nav_description
//    ),
//    NFC(
//        "nfc",
//        Icons.Default.Face,
//        R.string.biometric_nav_label,
//        R.string.biometric_nav_description
//    )
//}