package com.jaemak23.miniappsgalaxy.feature.markdownnotes.presentation.list

data class NoteListState(
    val notes: List<NoteUi> = emptyList(),
    val isLoading: Boolean = true
)

data class NoteUi(
    val id: String,
    val title: String,
    val preview: String,
    val formattedDate: String
)