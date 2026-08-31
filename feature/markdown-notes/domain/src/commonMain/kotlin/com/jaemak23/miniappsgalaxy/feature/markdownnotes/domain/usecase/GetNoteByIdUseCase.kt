package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.NoteLocalDataSource

class GetNoteByIdUseCase(private val dataSource: NoteLocalDataSource) {
    suspend operator fun invoke(id: String) = dataSource.getNoteById(id)
}