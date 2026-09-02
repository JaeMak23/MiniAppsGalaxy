package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.mapper

import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.DraftEntity
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.DraftNote

fun DraftEntity.toDraftNote(): DraftNote = DraftNote(
    filePath = filePath,
    title = title,
    content = content,
    updatedAt = updatedAt
)

fun DraftNote.toDraftEntity(): DraftEntity = DraftEntity(
    filePath = filePath,
    title = title,
    content = content,
    updatedAt = updatedAt
)