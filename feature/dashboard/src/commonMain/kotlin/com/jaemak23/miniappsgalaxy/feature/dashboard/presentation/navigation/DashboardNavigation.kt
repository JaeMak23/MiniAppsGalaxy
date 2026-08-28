package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.DashboardRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.DashboardScreen

@Composable
fun DashBoardNavigation(onLogout: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, DashboardRoute.DashBoard)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.goBack() }) { key ->
        when (key) {
            is DashboardRoute.DashBoard -> NavEntry(key) {
                DashboardScreen(onLogout = onLogout)
            }

            else -> NavEntry(key) {}
        }
    }
}

