package com.jaemak23.miniappsgalaxy.core.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.AdaptiveProvider
import com.jaemak23.miniappsgalaxy.core.ui.theme.AppTheme
import com.jaemak23.miniappsgalaxy.core.ui.theme.AppThemeFlavor
import com.jaemak23.miniappsgalaxy.core.ui.theme.ThemeManager
import com.jaemak23.miniappsgalaxy.core.ui.theme.colorScheme
import com.jaemak23.miniappsgalaxy.core.ui.theme.meshGradient

@Composable
fun AppContent(
    flavor: AppThemeFlavor = ThemeManager.Blue.flavor,
    content: @Composable () -> Unit
) {
    val systemDark = isSystemInDarkTheme()
    val darkMode = remember { mutableStateOf(systemDark) }
    val mesh = flavor.meshGradient(darkMode.value)
    val colorScheme = flavor.colorScheme(darkMode.value)
    val snackbarHostState = remember { SnackbarHostState() }

    AppTheme(colorScheme) {
        AdaptiveProvider(darkMode, mesh, snackbarHostState) {
            Box(Modifier.fillMaxSize()) {
                content()
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter)
                )
            }
        }
    }
}

