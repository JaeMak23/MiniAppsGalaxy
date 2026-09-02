package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.DashboardTabRoute
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.components.DummyBox
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.tabs.AppsTabPage
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.tabs.HomeTabScreen
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.tabs.ProfileTabScreen

@Composable
fun DashboardTabNavigation(
    backStack: NavBackStack<NavKey>,
    paddingValues: PaddingValues,
    onAppNavigation: (AppList) -> Unit,
    onLogout: () -> Unit
) {
    NavDisplay(
        modifier = Modifier.padding(paddingValues).fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.goBack() }
    ) { key ->

        when (key) {
            is DashboardTabRoute.Home -> NavEntry(key) {
                HomeTabScreen(onLogout)
            }

            is DashboardTabRoute.Apps -> NavEntry(key) {
                AppsTabPage(onAppNavigation)
            }

            is DashboardTabRoute.Games -> NavEntry(key) {
                DummyBox("Games Screen")
            }

            is DashboardTabRoute.Profile -> NavEntry(key) {
                ProfileTabScreen(onLogout)
            }

            else -> NavEntry(key) {

            }
        }
    }
}

enum class AppList {
    MarkdownNotes
}