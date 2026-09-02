package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

sealed interface NoteListAction {
    data object OnNewClick : NoteListAction
    data object OnImportClick : NoteListAction
    data object OnOpenFromDeviceClick : NoteListAction
    data class OnNoteClick(val noteId: String) : NoteListAction
    data class OnDeleteNote(val noteId: String) : NoteListAction
}