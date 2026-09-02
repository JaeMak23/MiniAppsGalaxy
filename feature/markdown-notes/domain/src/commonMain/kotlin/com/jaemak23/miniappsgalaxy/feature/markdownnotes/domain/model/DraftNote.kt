package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model

data class DraftNote(
    val filePath: String?,
    val title: String,
    val content: String,
    val updatedAt: Long
)