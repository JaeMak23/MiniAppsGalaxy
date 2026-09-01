package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

sealed interface NoteEditorEvent {
    data object NavigateBack : NoteEditorEvent
    data class LaunchSaveAsPicker(val suggestedName: String) : NoteEditorEvent // platform file picker for Save As
}