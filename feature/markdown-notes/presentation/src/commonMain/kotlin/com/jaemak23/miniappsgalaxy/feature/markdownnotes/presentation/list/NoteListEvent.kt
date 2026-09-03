package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

sealed interface NoteListEvent {
    data object NavigateToNewNote : NoteListEvent
    data class NavigateToEditor(val noteId: String) : NoteListEvent

    data class NavigateToImportedNote(val noteId: String) : NoteListEvent

    data class NavigateToOpenedDraft(val filePath: String?) : NoteListEvent }