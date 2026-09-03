package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

sealed interface NoteEditorAction {
    data class OnTitleChange(val title: String) : NoteEditorAction
    data class OnContentChange(val content: String) : NoteEditorAction
    data object OnBackClick : NoteEditorAction
    data object OnSaveToListClick : NoteEditorAction   // draft -> becomes a real list note
    data object OnSaveToDeviceClick : NoteEditorAction // export; Save/Replace if filePath known, else Save As
    // view mode
    data class OnSetViewMode(val mode: EditorViewMode) : NoteEditorAction
    data class OnDragRatio(val ratio: Float) : NoteEditorAction // live drag updates, clamped+snap-checked in VM
}