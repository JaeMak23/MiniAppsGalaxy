package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalSnackbarHostState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun NoteListRoot(
    onNavigateToNewNote: () -> Unit,
    onNavigateToEditor: (String) -> Unit,
    onNavigateToImportedNote: (String) -> Unit,
    onNavigateToOpenedDraft: (String?) -> Unit,
    onExit: () -> Unit,
    viewModel: NoteListViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackbarHostState.current
    val scope = rememberCoroutineScope()

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            NoteListEvent.NavigateToNewNote -> onNavigateToNewNote()
            is NoteListEvent.NavigateToEditor -> onNavigateToEditor(event.noteId)
            is NoteListEvent.NavigateToImportedNote -> onNavigateToImportedNote(event.noteId)
            is NoteListEvent.NavigateToOpenedDraft -> onNavigateToOpenedDraft(event.filePath)
            is NoteListEvent.ShowMessage -> scope.launch { snackbarHostState.showSnackbar(event.message) }
        }
    }

    NoteListScreen(state = state, onAction = viewModel::onAction,onExit=onExit)
}
