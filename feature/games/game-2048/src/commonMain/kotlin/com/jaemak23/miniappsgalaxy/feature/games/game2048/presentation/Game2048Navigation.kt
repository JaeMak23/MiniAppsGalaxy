package com.jaemak23.miniappsgalaxy.feature.games.game2048.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.core.navigation.routes.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.routes.games.Game2048Route

@Composable
fun Game2048Navigation(onExit: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, Game2048Route.Home)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { if (!backStack.goBack()) onExit() }) { key ->
        when (key) {
            is Game2048Route.Home -> NavEntry(key) { Game2048Root(onBack = onExit) }

            else -> NavEntry(key) {}
        }
    }
}