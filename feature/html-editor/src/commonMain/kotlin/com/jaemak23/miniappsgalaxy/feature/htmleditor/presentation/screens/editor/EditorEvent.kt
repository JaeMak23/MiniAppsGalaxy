package com.jaemak23.miniappsgalaxy.feature.htmleditor.presentation.screens.editor

import com.jaemak23.miniappsgalaxy.core.ui.error.UiText

sealed interface EditorEvent {
    data object NavigateBack : EditorEvent
    data class ShowSnackbar(val message: UiText) : EditorEvent
}