package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.navigation.HtmlEditorRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.editor.EditorRoot
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.home.HomeRoot

@Composable
fun HtmlEditorNavigation(onExit: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, HtmlEditorRoute.Home)

    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { if (!backStack.goBack()) onExit() }) { key ->
        when (key) {
            is HtmlEditorRoute.Home -> NavEntry(key) {
                HomeRoot(
                    onExit = onExit,
                    onNavigateToEditor = { file ->
                        backStack.add(
                            HtmlEditorRoute.Editor(
                                title = file.title,
                                filePath = file.filePath
                            )
                        )
                    })
            }

            is HtmlEditorRoute.Editor -> NavEntry(key) {
                EditorRoot(
                    title = key.title,
                    filePath = key.filePath,
                    onBack = { backStack.goBack() }
                )
            }

            else -> NavEntry(key) {}
        }
    }
}