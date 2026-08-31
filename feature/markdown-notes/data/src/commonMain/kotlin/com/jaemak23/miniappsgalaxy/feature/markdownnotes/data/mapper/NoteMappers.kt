package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.mapper

import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.NoteEntity
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.Note

fun NoteEntity.toNote(): Note = Note(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    updatedAt = updatedAt
)

fun Note.toNoteEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    content = content,
    createdAt = createdAt,
    updatedAt = updatedAt
)