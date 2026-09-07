package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.core.navigation.DashboardRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.components.DummyBox
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.DashboardScreen
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.navigation.MarkdownNotesNavigation

@Composable
fun DashBoardNavigation(onLogout: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, DashboardRoute.DashBoard)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { backStack.goBack() }) { key ->
        when (key) {
            is DashboardRoute.DashBoard -> NavEntry(key) {
                DashboardScreen(onLogout = onLogout, onAppNavigation = { app ->
                    when (app) {
                        AppList.MarkdownNotes -> backStack.add(DashboardRoute.MarkdownNotes)
                        AppList.HtmlEditor -> backStack.add(DashboardRoute.HtmlEditor)
                        AppList.NoteEditor -> backStack.add(DashboardRoute.NoteEditor)
                    }
                })
            }

            is DashboardRoute.MarkdownNotes -> NavEntry(key) {
                MarkdownNotesNavigation { backStack.goBack() }
            }
            is DashboardRoute.HtmlEditor -> NavEntry(key) {
                DummyBox("HTML Editor sample"){backStack.goBack()}
            }
            is DashboardRoute.NoteEditor -> NavEntry(key) {
                DummyBox("Note Editor sample"){backStack.goBack()}
            }

            else -> NavEntry(key) {}
        }
    }
}

