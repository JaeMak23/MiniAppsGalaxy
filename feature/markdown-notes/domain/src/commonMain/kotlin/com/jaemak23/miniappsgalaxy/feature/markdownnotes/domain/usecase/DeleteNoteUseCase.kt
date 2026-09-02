package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.NoteLocalDataSource

class DeleteNoteUseCase(private val dataSource: NoteLocalDataSource) {
    suspend operator fun invoke(id: String) = dataSource.deleteNote(id)
}