package com.kis.nativka.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.PrimaryTabRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.kis.nativka.biometric.presentation.BiometricRoute

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun AppMainScreen(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val defaultTab = TabNavItem.Biometric

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.safeDrawing)
    ) { contentPadding ->
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        Column(modifier = Modifier.fillMaxSize()) {
            PrimaryTabRow(
                selectedTabIndex = TabNavItem.items
                    .indexOfFirst { it.route == currentRoute }
                    .coerceAtLeast(0),
                modifier = Modifier.fillMaxWidth()
            ) {
                TabNavItem.items.forEach { item ->
                    AppTab(item, item.route == currentRoute) {
                        navigateToTab(navController, item.route)
                    }
                }
            }
            AppNavHost(
                navController = navController,
                defaultStartTab = defaultTab.route,
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    if (navController.currentDestination?.route != route) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

@Composable
fun AppTab(
    item: TabNavItem,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Tab(
        selected = isSelected,
        onClick = onClick,
        text = {
            Text(
                text = stringResource(item.label),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        icon = {
            Icon(
                imageVector = item.icon,
                contentDescription = stringResource(item.contentDescription)
            )
        }
    )
}

@Composable
private fun AppNavHost(
    navController: NavHostController,
    defaultStartTab: String,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = defaultStartTab,
        modifier = modifier
    ) {
        TabNavItem.items.forEach { item ->
            composable(item.route) {
                when (item) {
                    TabNavItem.Biometric -> BiometricRoute()
                    TabNavItem.TEE -> Text("TEE")
                    TabNavItem.NFC -> Text("NFC")
                    TabNavItem.BLE -> Text("BLE")
                    TabNavItem.OMAPI -> Text("OMAPI")
                }
            }
        }
    }
}