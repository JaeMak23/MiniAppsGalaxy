package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.editor

sealed interface EditorViewMode {
    data object EditorOnly : EditorViewMode
    data object PreviewOnly : EditorViewMode
    data class Split(val ratio: Float = 0.5f) : EditorViewMode // 0f..1f, editor's share of space
}