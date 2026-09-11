package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.core.ui.error.toUiText
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.usecase.CreateBlankFileUseCase
import com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.domain.usecase.ImportHtmlFileUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val createBlankFileUseCase: CreateBlankFileUseCase,
    private val importHtmlFileUseCase: ImportHtmlFileUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    private val _events = Channel<HomeEvent>()
    val events = _events.receiveAsFlow()

    fun onAction(action: HomeAction) {
        when (action) {
            HomeAction.OnCreateBlankFileClick -> createBlankFile()
            HomeAction.OnImportFileClick -> importFile()
        }
    }

    private fun createBlankFile() {
        viewModelScope.launch {
            _events.send(HomeEvent.NavigateToEditor(createBlankFileUseCase()))
        }
    }

    private fun importFile() {
        viewModelScope.launch {
            _state.update { it.copy(isImporting = true) }

            when (val result = importHtmlFileUseCase()) {
                is Result.Success -> {
                    _state.update { it.copy(isImporting = false) }
                    result.data?.let { file ->
                        _events.send(HomeEvent.NavigateToEditor(file))
                    }
                    // null = user cancelled picker, no-op
                }

                is Result.Error -> {
                    _state.update { it.copy(isImporting = false) }
                    _events.send(HomeEvent.ShowSnackbar(result.error.toUiText()))
                }
            }
        }
    }
}