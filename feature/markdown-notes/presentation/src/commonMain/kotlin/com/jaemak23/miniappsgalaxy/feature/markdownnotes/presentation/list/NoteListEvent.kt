package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

sealed interface NoteListEvent {
    data object NavigateToNewNote : NoteListEvent
    data class NavigateToEditor(val noteId: String) : NoteListEvent

    // list decides Import happened; picker itself is platform UI
    data object LaunchImportPicker : NoteListEvent

    // Open-from-device — draft flow, no list entry
    data object LaunchOpenPicker : NoteListEvent
}