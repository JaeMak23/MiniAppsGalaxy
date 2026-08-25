package com.jaemak23.miniappsgalaxy.core.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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

    AppTheme(colorScheme) {
        AdaptiveProvider(darkModeState, mesh, content)
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