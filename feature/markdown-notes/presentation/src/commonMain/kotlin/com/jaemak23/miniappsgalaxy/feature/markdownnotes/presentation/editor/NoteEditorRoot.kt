package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteEditorRoot(
    origin: NoteEditorOrigin,
    onNavigateBack: () -> Unit,
    onLaunchSaveAsPicker: (suggestedName: String) -> Unit,
    viewModel: NoteEditorViewModel = koinViewModel { parametersOf(origin) }
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    // ObserveAsEvents(...) as before
    NoteEditorScreen(state = state, onAction = viewModel::onAction)
}

