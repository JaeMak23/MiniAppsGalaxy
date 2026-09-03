package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteListRoot(
    onNavigateToNewNote: () -> Unit,
    onNavigateToEditor: (String) -> Unit,
    onNavigateToImportedNote: (String) -> Unit,
    onLaunchOpenPicker: () -> Unit,
    viewModel: NoteListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            NoteListEvent.NavigateToNewNote -> onNavigateToNewNote()
            is NoteListEvent.NavigateToEditor -> onNavigateToEditor(event.noteId)
            is NoteListEvent.NavigateToImportedNote -> onNavigateToImportedNote(event.noteId)
            NoteListEvent.LaunchOpenPicker -> onLaunchOpenPicker()
        }
    }

    NoteListScreen(state = state, onAction = viewModel::onAction)
}
