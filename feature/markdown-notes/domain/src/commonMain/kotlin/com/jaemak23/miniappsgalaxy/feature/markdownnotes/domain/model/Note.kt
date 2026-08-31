package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model

data class Note(
    val id: String,
    val title: String,
    val content: String, // raw markdown
    val createdAt: Long,
    val updatedAt: Long
)