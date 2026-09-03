package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jaemak23.miniappsgalaxy.core.common.util.debugPrint
import com.jaemak23.miniappsgalaxy.core.domain.onSuccess
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.DeleteNoteUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.Note
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.GetNotesUseCase
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase.ImportNoteUseCase
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

class NoteListViewModel(
    private val getNotes: GetNotesUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val importNote: ImportNoteUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(NoteListState())
    val state = _state.asStateFlow()

    private val _events = Channel<NoteListEvent>()
    val events = _events.receiveAsFlow()

    init {
        debugPrint("NoteListViewModel Initialized")
        observeNotes()
    }

    fun onAction(action: NoteListAction) {
        when (action) {
            NoteListAction.OnNewClick -> sendEvent(NoteListEvent.NavigateToNewNote)
            NoteListAction.OnImportClick -> handleImport()
            NoteListAction.OnOpenFromDeviceClick -> sendEvent(NoteListEvent.LaunchOpenPicker)
            is NoteListAction.OnNoteClick -> sendEvent(NoteListEvent.NavigateToEditor(action.noteId))
            is NoteListAction.OnDeleteNote -> viewModelScope.launch { deleteNote(action.noteId) }

        }
    }

    private fun sendEvent(event: NoteListEvent) {
        viewModelScope.launch { _events.send(event) }
    }

    private fun handleImport() {
        viewModelScope.launch {
            importNote().onSuccess { noteId ->
                if (noteId != null) { // null = user canceled the file picker
                    _events.send(NoteListEvent.NavigateToImportedNote(noteId))
                }
            }
            // Result.Error case: silently ignored for now — see note below
        }
    }

    private fun observeNotes() {
        debugPrint("Called observeNotes()")

        getNotes()
            .onEach { notes ->
                _state.update {
                    it.copy(notes = notes.map { note -> note.toNoteUi() }, isLoading = false)
                }
            }
            .launchIn(viewModelScope)
    }

    override fun onCleared() {
        super.onCleared()
        debugPrint("NoteListViewModel cleared")
    }
}

private fun Note.toNoteUi(): NoteUi = NoteUi(
    id = id,
    title = title,
    preview = content.take(100).replace(Regex("[#*_`>\\-\\[\\]()]"), "").trim(),
    formattedDate = Instant.fromEpochMilliseconds(updatedAt)
        .toLocalDateTime(TimeZone.currentSystemDefault())
        .let { "${it.month.name.take(3)} ${it.day}, ${it.year}" }
)