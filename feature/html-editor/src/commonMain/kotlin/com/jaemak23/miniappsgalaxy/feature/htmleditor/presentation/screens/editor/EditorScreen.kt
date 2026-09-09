package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaemak23.miniappsgalaxy.core.ui.components.ExitButton
import com.jaemak23.miniappsgalaxy.core.ui.components.FileNameTitleTextField
import com.jaemak23.miniappsgalaxy.core.ui.components.ThemeActionButton

@Composable
fun EditorScreen(onExit: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    FileNameTitleTextField("state.title", "Note Title") {
                    }
                },
                navigationIcon = {},
                actions = {
                    ThemeActionButton()
                    ExitButton { onExit() }
                }
            )
        }
    ) { padding ->
        Box(modifier = Modifier.fillMaxSize().padding(padding)) {

        }
    }
}