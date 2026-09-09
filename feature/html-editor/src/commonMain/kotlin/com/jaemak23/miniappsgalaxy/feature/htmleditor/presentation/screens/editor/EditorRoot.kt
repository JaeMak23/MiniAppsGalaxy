package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor

import androidx.compose.runtime.Composable
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState

@Composable
fun EditorRoot(
    onBack: () -> Unit
) {
    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = true,
        onBackCompleted = { onBack() },
        onBackCancelled = { }
    )

    EditorScreen(onBack)
}