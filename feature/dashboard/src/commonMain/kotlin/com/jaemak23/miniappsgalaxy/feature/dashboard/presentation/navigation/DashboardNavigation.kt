package com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.AppList
import com.jaemak23.miniappsgalaxy.core.navigation.GameList
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.core.navigation.routes.DashboardRoute
import com.jaemak23.miniappsgalaxy.core.navigation.routes.NavConfig
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.navigation.HtmlEditorNavigation
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.presentation.navigation.MarkdownNotesNavigation
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.components.DummyBox
import com.jaemak23.miniappsgalaxy.feature.dashboard.presentation.screens.dashboardmain.DashboardScreen
import com.jaemak23.miniappsgalaxy.feature.games.tictactoe.presentation.navigation.TicTacToeNavigation

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
                }, onGameNavigation = {game->
                    when(game) {
                        GameList.TicTacToe-> backStack.add(DashboardRoute.TicTacToe)
                    }
                })
            }

            is DashboardRoute.MarkdownNotes -> NavEntry(key) {
                MarkdownNotesNavigation { backStack.goBack() }
            }
            is DashboardRoute.HtmlEditor -> NavEntry(key) {
                HtmlEditorNavigation { backStack.goBack() }
            }
            is DashboardRoute.NoteEditor -> NavEntry(key) {
                DummyBox("Note Editor sample"){backStack.goBack()}
            }
            is DashboardRoute.TicTacToe -> NavEntry(key) {
                TicTacToeNavigation {backStack.goBack()}
            }

            else -> NavEntry(key) {}
        }
    }
}

