package com.jaemak23.miniappsgalaxy.feature.apps.htmleditor.presentation.screens.editor

import com.jaemak23.miniappsgalaxy.core.ui.components.composeeditorkit.EditorViewMode

sealed interface EditorAction {
    data class OnTitleChange(val title: String) : EditorAction
    data class OnContentChange(val content: String) : EditorAction
    data class OnSetViewMode(val mode: EditorViewMode) : EditorAction
    data class OnDragRatio(val ratio: Float) : EditorAction
    data object OnBackClick : EditorAction
    data object OnSaveClick : EditorAction
    data object OnRefreshPreviewClick : EditorAction
}