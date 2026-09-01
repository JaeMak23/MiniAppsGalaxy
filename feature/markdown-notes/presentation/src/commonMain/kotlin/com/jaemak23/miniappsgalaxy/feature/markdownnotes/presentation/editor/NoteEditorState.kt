package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

data class NoteEditorState(
    val noteId: String? = null,
    val title: String = "",
    val content: String = "",
    val createdAt: Long? = null,
    val isPreviewMode: Boolean = false,
    val isLoading: Boolean = false
)

sealed interface NoteEditorAction {
    data class OnTitleChange(val title: String) : NoteEditorAction
    data class OnContentChange(val content: String) : NoteEditorAction
    data object OnTogglePreview : NoteEditorAction
    data object OnSaveClick : NoteEditorAction
    data object OnBackClick : NoteEditorAction
}

sealed interface NoteEditorEvent {
    data object NavigateBack : NoteEditorEvent
}