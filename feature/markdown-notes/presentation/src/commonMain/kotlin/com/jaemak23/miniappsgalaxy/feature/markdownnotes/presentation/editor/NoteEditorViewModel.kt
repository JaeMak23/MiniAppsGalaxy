package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.core.domain.onSuccess
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetNoteByIdUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteEditorViewModel(
    savedStateHandle: SavedStateHandle,
    private val getNoteById: GetNoteByIdUseCase,
    private val saveNote: SaveNoteUseCase
) : ViewModel() {

    // read the incoming noteId from nav args (nullable = new note)
    private val noteId: String? = savedStateHandle["noteId"]

    private val _state = MutableStateFlow(
        NoteEditorState(
            noteId = noteId,
            title = savedStateHandle["title"] ?: "",
            content = savedStateHandle["content"] ?: ""
        )
    )
    val state = _state.asStateFlow()

    private val _events = Channel<NoteEditorEvent>()
    val events = _events.receiveAsFlow()

    private val handle = savedStateHandle

    init {
        if (noteId != null && handle.get<String>("content") == null) {
            loadNote(noteId)
        }
    }

    fun onAction(action: NoteEditorAction) {
        when (action) {
            is NoteEditorAction.OnTitleChange -> {
                handle["title"] = action.title
                _state.update { it.copy(title = action.title) }
            }
            is NoteEditorAction.OnContentChange -> {
                handle["content"] = action.content
                _state.update { it.copy(content = action.content) }
            }
            NoteEditorAction.OnTogglePreview ->
                _state.update { it.copy(isPreviewMode = !it.isPreviewMode) }
            NoteEditorAction.OnSaveClick -> save()
            NoteEditorAction.OnBackClick -> save()
        }
    }

    private fun loadNote(id: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getNoteById(id).onSuccess { note ->
                handle["title"] = note.title
                handle["content"] = note.content
                _state.update {
                    it.copy(
                        title = note.title,
                        content = note.content,
                        createdAt = note.createdAt,
                        isLoading = false
                    )
                }
            }
        }
    }

    private fun save() {
        viewModelScope.launch {
            val s = _state.value
            if (s.title.isNotBlank() || s.content.isNotBlank()) {
                saveNote(
                    id = s.noteId,
                    title = s.title,
                    content = s.content,
                    createdAt = s.createdAt
                )
            }
            _events.send(NoteEditorEvent.NavigateBack)
        }
    }
}