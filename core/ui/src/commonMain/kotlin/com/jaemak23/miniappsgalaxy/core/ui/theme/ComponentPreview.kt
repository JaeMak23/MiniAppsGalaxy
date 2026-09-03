package com.jaemak23.miniappsgalaxy.core.ui.theme

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.AppContent
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.AdaptiveProvider
import org.koin.compose.KoinApplication
import org.koin.core.module.Module
import org.koin.dsl.koinConfiguration

@Composable
fun ComponentPreview(
    flavor: AppThemeFlavor = ThemeManager.Blue.flavor,
    darkMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val darkModeState = remember { mutableStateOf(darkMode) }
    val mesh = flavor.meshGradient(darkModeState.value)
    val colorScheme = flavor.colorScheme(darkModeState.value)
    val snackbarHostState = remember { SnackbarHostState() }

    AppTheme(colorScheme) {
        AdaptiveProvider(darkModeState, mesh, snackbarHostState){
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

@Composable
fun AppPreview(
    flavor: AppThemeFlavor = ThemeManager.Blue.flavor,
    modules: List<Module> = emptyList(),
    content: @Composable () -> Unit
) {
    KoinApplication(
        configuration = koinConfiguration { modules(modules) }
    ) {
        AppContent(flavor, content)
    }
}