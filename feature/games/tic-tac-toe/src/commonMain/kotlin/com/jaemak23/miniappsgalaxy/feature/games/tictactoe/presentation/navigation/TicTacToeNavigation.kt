package com.jaemak23.miniappsgalaxy.feature.games.tictactoe.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.core.navigation.routes.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.routes.TicTacToeRoute
import com.jaemak23.miniappsgalaxy.feature.games.tictactoe.presentation.ui.screens.TicTacToeGame

@Composable
fun TicTacToeNavigation(onExit: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, TicTacToeRoute.Home)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { if (!backStack.goBack()) onExit() }) { key ->
        when (key) {
            is TicTacToeRoute.Home -> NavEntry(key) { TicTacToeGame(onExit = onExit) }

            else -> NavEntry(key) {}
        }
    }
}