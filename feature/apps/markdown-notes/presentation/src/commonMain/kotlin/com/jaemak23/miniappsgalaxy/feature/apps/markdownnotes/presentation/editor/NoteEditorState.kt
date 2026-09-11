package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.presentation.editor

import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorViewMode

data class NoteEditorState(
    val origin: NoteEditorOrigin = NoteEditorOrigin.New,
    val noteId: String? = null,
    val title: String = "",
    val content: String = "",
    val createdAt: Long? = null,
    val viewMode: EditorViewMode = EditorViewMode.EditorOnly,
    val isLoading: Boolean = false,
    val isDraftMode: Boolean = false // true only for FromOpen — hides "Save to list" ambiguity, shows explicit choices
)