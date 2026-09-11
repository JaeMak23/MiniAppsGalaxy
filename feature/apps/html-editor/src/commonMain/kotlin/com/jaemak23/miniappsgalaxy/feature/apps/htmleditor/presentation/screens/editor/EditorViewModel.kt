package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.editor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.core.domain.FileAccessDataSource
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorViewMode
import com.jaemak23.miniappsgalaxy.core.ui.error.toUiText
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.usecase.SaveHtmlFileUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class EditorViewModel(
    private val title: String,
    private val filePath: String?,
    private val fileAccess: FileAccessDataSource,
    private val saveHtmlFileUseCase: SaveHtmlFileUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(
        EditorState(title = title, filePath = filePath)
    )
    val state = _state.asStateFlow()

    private val _events = Channel<EditorEvent>()
    val events = _events.receiveAsFlow()

    init {
        if (filePath != null) {
            loadFile(filePath)
        } else {
            _state.update { it.copy(content = DEFAULT_HTML_TEMPLATE, previewHtml = DEFAULT_HTML_TEMPLATE) }
        }
    }

    private fun loadFile(filePath: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            when (val result = fileAccess.readFile(filePath)) {
                is Result.Success -> _state.update {
                    it.copy(content = result.data, previewHtml = result.data, isLoading = false)
                }
                is Result.Error -> {
                    _state.update { it.copy(isLoading = false) }
                    _events.send(EditorEvent.ShowSnackbar(result.error.toUiText()))
                }
            }
        }
    }

    fun onAction(action: EditorAction) {
        when (action) {
            is EditorAction.OnTitleChange -> _state.update { it.copy(title = action.title) }
            is EditorAction.OnContentChange -> _state.update { it.copy(content = action.content) }
            is EditorAction.OnSetViewMode -> _state.update { it.copy(viewMode = action.mode) }
            is EditorAction.OnDragRatio -> {
                val mode = _state.value.viewMode
                if (mode is EditorViewMode.Split) {
                    _state.update { it.copy(viewMode = mode.copy(ratio = action.ratio.coerceIn(0.1f, 0.9f))) }
                }
            }
            EditorAction.OnBackClick -> viewModelScope.launch { _events.send(EditorEvent.NavigateBack) }
            EditorAction.OnSaveClick -> saveFile()
            EditorAction.OnRefreshPreviewClick -> {
                _state.update { it.copy(previewHtml = it.content) }
            }
        }
    }

    private fun saveFile() {
        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val current = _state.value
            when (val result = saveHtmlFileUseCase(
                filePath = current.filePath,
                suggestedName = current.title,
                content = current.content
            )) {
                is Result.Success -> {
                    _state.update { it.copy(isSaving = false, filePath = result.data ?: it.filePath) }
                }
                is Result.Error -> {
                    _state.update { it.copy(isSaving = false) }
                    _events.send(EditorEvent.ShowSnackbar(result.error.toUiText()))
                }
            }
        }
    }

    companion object {
        private const val DEFAULT_HTML_TEMPLATE = "<!DOCTYPE html>\n<html>\n<head>\n    <title>Untitled</title>\n</head>\n<body>\n\n</body>\n</html>"
    }
}