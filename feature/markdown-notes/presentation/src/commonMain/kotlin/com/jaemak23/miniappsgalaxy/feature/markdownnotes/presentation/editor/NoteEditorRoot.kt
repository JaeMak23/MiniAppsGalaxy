package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun NoteEditorRoot(
    origin: NoteEditorOrigin,
    instantKey: String, // pass MarkdownNotesRoute.Editor.instanceId in from the nav call site
    onNavigateBack: () -> Unit,
    viewModel: NoteEditorViewModel = koinViewModel(
        key = instantKey,
        parameters = { parametersOf(origin) })
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    NavigationBackHandler(
        state = rememberNavigationEventState( NavigationEventInfo.None),
        isBackEnabled = true,
        onBackCompleted = { viewModel.onAction(NoteEditorAction.OnBackClick) },
        onBackCancelled = { }
    )

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            NoteEditorEvent.NavigateBack -> onNavigateBack()
        }
    }

    NoteEditorScreen(state = state, onAction = viewModel::onAction)
}