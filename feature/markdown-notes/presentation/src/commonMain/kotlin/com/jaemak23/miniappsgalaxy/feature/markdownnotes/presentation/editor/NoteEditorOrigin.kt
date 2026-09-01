package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

sealed interface NoteEditorOrigin {
    data object New : NoteEditorOrigin
    data class FromList(val noteId: String) : NoteEditorOrigin
    data class FromImport(val noteId: String) : NoteEditorOrigin
    data class FromOpen(val filePath: String?) : NoteEditorOrigin
}