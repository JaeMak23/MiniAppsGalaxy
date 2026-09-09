package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.jaemak23.miniappsgalaxy.core.common.util.debugPrint
import com.jaemak23.miniappsgalaxy.core.navigation.HtmlEditorRoute
import com.jaemak23.miniappsgalaxy.core.navigation.NavConfig
import com.jaemak23.miniappsgalaxy.core.navigation.goBack
import com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor.EditorRoot
import com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.home.HomeRoot

@Composable
fun HtmlEditorNavigation(onExit: () -> Unit) {
    val backStack = rememberNavBackStack(NavConfig, HtmlEditorRoute.Home)

    debugPrint("HTML Editor Navigation")
    NavDisplay(
        modifier = Modifier.fillMaxSize(),
        backStack = backStack,
        onBack = { if (!backStack.goBack()) onExit() }) { key ->
        when (key) {
            is HtmlEditorRoute.Home -> NavEntry(key) {
                HomeRoot(onExit, { backStack.add(HtmlEditorRoute.Editor) })
            }

            is HtmlEditorRoute.Editor -> NavEntry(key) {
                EditorRoot { backStack.goBack() }
            }

            else -> NavEntry(key) {}
        }
    }
}