package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.core.domain.onSuccess
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.ClearDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.ExportNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetNoteByIdUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.SaveDraftUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.SaveNoteUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.milliseconds
import com.jaemak23.miniappsgalaxy.core.domain.Result

class NoteEditorViewModel(
    private val origin: NoteEditorOrigin, // now injected directly, not parsed from SavedStateHandle
    private val savedStateHandle: SavedStateHandle,
    private val getNoteById: GetNoteByIdUseCase,
    private val saveNote: SaveNoteUseCase,
    private val saveDraft: SaveDraftUseCase,
    private val getDraft: GetDraftUseCase,
    private val clearDraft: ClearDraftUseCase,
    private val exportNote: ExportNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(
        NoteEditorState(
            origin = origin,
            noteId = (origin as? NoteEditorOrigin.FromList)?.noteId
                ?: (origin as? NoteEditorOrigin.FromImport)?.noteId,
            title = savedStateHandle["title"] ?: "",
            content = savedStateHandle["content"] ?: "",
            isDraftMode = origin is NoteEditorOrigin.FromOpen
        )
    )

    val state = _state.asStateFlow()

    private val _events = Channel<NoteEditorEvent>()
    val events = _events.receiveAsFlow()

    private var draftAutoSaveJob: Job? = null

    private val handle = savedStateHandle
    private var hasSaved = false

    init {
        val alreadyRestoredFromHandle = savedStateHandle.get<String>("content") != null
        if (!alreadyRestoredFromHandle) {
            when (origin) {
                is NoteEditorOrigin.FromList -> loadExistingNote(origin.noteId)
                is NoteEditorOrigin.FromImport -> loadExistingNote(origin.noteId)
                is NoteEditorOrigin.FromOpen -> loadDraftIfPresent()
                NoteEditorOrigin.New -> Unit
            }
        }
    }

    fun onAction(action: NoteEditorAction) {
        when (action) {
            is NoteEditorAction.OnTitleChange -> updateField(title = action.title)
            is NoteEditorAction.OnContentChange -> updateField(content = action.content)

            NoteEditorAction.OnBackClick -> onBack()
            NoteEditorAction.OnSaveToListClick -> saveDraftToList()
            NoteEditorAction.OnSaveToDeviceClick -> requestSaveToDevice()
            NoteEditorAction.OnExportClick -> handleExportAppOnlyNote()

            is NoteEditorAction.OnSetViewMode -> _state.update { it.copy(viewMode = action.mode) }
            is NoteEditorAction.OnDragRatio -> _state.update {
                it.copy(
                    viewMode = resolveDragRatio(
                        action.ratio
                    )
                )
            }
        }
    }

    private fun updateField(title: String? = null, content: String? = null) {
        title?.let { savedStateHandle["title"] = it }
        content?.let { savedStateHandle["content"] = it }
        _state.update {
            it.copy(
                title = title ?: it.title,
                content = content ?: it.content
            )
        }
        if (_state.value.isDraftMode) scheduleDraftAutoSave()
    }

    /** Debounced auto-save into the single draft slot — FromOpen only. */
    private fun scheduleDraftAutoSave() {
        draftAutoSaveJob?.cancel()
        draftAutoSaveJob = viewModelScope.launch {
            delay(500.milliseconds)
            val s = _state.value
            val filePath = (s.origin as? NoteEditorOrigin.FromOpen)?.filePath
            saveDraft(filePath = filePath, title = s.title, content = s.content)
        }
    }

    private fun loadExistingNote(noteId: String) {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getNoteById(noteId).onSuccess { note ->
                savedStateHandle["title"] = note.title
                savedStateHandle["content"] = note.content
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

    private fun loadDraftIfPresent() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            getDraft().onSuccess { draft ->
                if (draft != null) {
                    savedStateHandle["title"] = draft.title
                    savedStateHandle["content"] = draft.content
                    _state.update {
                        it.copy(
                            title = draft.title,
                            content = draft.content,
                            isLoading = false
                        )
                    }
                } else {
                    _state.update { it.copy(isLoading = false) }
                }
            }
        }
    }

    private fun onBack() {
        viewModelScope.launch {
            if (!hasSaved) {
                hasSaved = true
                when (origin) {
                    NoteEditorOrigin.New, is NoteEditorOrigin.FromList, is NoteEditorOrigin.FromImport -> {
                        val s = _state.value
                        if (s.title.isNotBlank() || s.content.isNotBlank()) {
                            saveNote(
                                id = s.noteId,
                                title = s.title,
                                content = s.content,
                                createdAt = s.createdAt
                            )
                            _events.send(NoteEditorEvent.ShowMessage("Note saved"))

                        }
                    }

                    is NoteEditorOrigin.FromOpen -> Unit
                }
            }
            _events.send(NoteEditorEvent.NavigateBack)
        }
    }

    /** Draft -> becomes a real list note (App Only from this point on); draft slot is cleared. */
    private fun saveDraftToList() {
        viewModelScope.launch {
            val s = _state.value
            saveNote(id = null, title = s.title, content = s.content, createdAt = null)
            clearDraft()
            _events.send(NoteEditorEvent.ShowMessage("Note added to list"))
            _events.send(NoteEditorEvent.NavigateBack)
        }
    }

    private fun requestSaveToDevice() {
        viewModelScope.launch {
            val s = _state.value
            val currentFilePath = (s.origin as? NoteEditorOrigin.FromOpen)?.filePath

            exportNote(
                filePath = currentFilePath,
                suggestedName = s.title.ifBlank { "note" },
                content = s.content
            ).let { result ->
                when (result) {
                    is Result.Success -> {
                        if (result.data != null) {
                            clearDraft()
                            _events.send(NoteEditorEvent.ShowMessage("File saved successfully"))
                            _events.send(NoteEditorEvent.NavigateBack)
                        }
                    }

                    is Result.Error -> {
                        _events.send(NoteEditorEvent.ShowMessage("Couldn't save file — try again"))
                    }
                }
            }
        }
    }

    private fun handleExportAppOnlyNote() {
        viewModelScope.launch {
            val s = _state.value
            when (val result = exportNote(
                filePath = null,
                suggestedName = s.title.ifBlank { "note" },
                content = s.content
            )) {
                is Result.Success -> {
                    if (result.data != null) {
                        _events.send(NoteEditorEvent.ShowMessage("Exported successfully"))
                    }
                }

                is Result.Error -> _events.send(NoteEditorEvent.ShowMessage("Couldn't export — try again"))
            }
        }
    }

    private fun resolveDragRatio(rawRatio: Float): EditorViewMode {
        val clamped = rawRatio.coerceIn(0f, 1f)
        return when {
            clamped >= 0.9f -> EditorViewMode.EditorOnly
            clamped <= 0.1f -> EditorViewMode.PreviewOnly
            else -> EditorViewMode.Split(clamped)
        }
    }
}