package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.editor

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalSnackbarHostState
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun EditorRoot(
    title: String,
    filePath: String?,
    onBack: () -> Unit,
    viewModel: EditorViewModel = koinViewModel(
        key = "editor-$filePath-$title",
        parameters = { parametersOf(title, filePath) }
    )
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackbarHostState.current
    val scope = rememberCoroutineScope()

    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = true,
        onBackCompleted = { onBack() },
        onBackCancelled = { }
    )

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            EditorEvent.NavigateBack -> onBack()
            is EditorEvent.ShowSnackbar -> {
                scope.launch { snackbarHostState.showSnackbar(event.message.value) }
            }
        }
    }

    EditorScreen(
        state = state,
        onAction = viewModel::onAction
    )
}