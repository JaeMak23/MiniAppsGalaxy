package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.home

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigationevent.NavigationEventInfo
import androidx.navigationevent.compose.NavigationBackHandler
import androidx.navigationevent.compose.rememberNavigationEventState
import com.jaemak23.miniappsgalaxy.core.ui.ObserveAsEvents
import com.jaemak23.miniappsgalaxy.core.ui.adaptive.LocalSnackbarHostState
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.model.HtmlFile
import kotlinx.coroutines.launch
import org.koin.compose.viewmodel.koinViewModel

data class HtmlFileUi(
    val id: String,
    val name: String,
    val formattedModifiedDate: String
)

@Composable
fun HomeRoot(
    onExit: () -> Unit,
    onNavigateToEditor: (HtmlFile) -> Unit,
    viewModel: HomeViewModel = koinViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = LocalSnackbarHostState.current
    val scope = rememberCoroutineScope()

    NavigationBackHandler(
        state = rememberNavigationEventState(NavigationEventInfo.None),
        isBackEnabled = true,
        onBackCompleted = onExit,
        onBackCancelled = { }
    )

    ObserveAsEvents(viewModel.events) { event ->
        when (event) {
            is HomeEvent.NavigateToEditor -> onNavigateToEditor(event.file)
            is HomeEvent.ShowSnackbar -> {
                scope.launch { snackbarHostState.showSnackbar(event.message.value) }
            }
        }
    }

    HomeScreen(
        state = state,
        onAction = viewModel::onAction,
        onExit = onExit
    )
}

