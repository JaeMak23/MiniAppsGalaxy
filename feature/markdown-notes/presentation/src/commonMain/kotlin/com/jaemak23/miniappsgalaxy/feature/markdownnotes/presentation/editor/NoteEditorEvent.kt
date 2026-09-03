package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

sealed interface NoteEditorEvent {
    data object NavigateBack : NoteEditorEvent
}