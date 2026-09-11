package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.NoteLocalDataSource

class DeleteNoteUseCase(private val dataSource: NoteLocalDataSource) {
    suspend operator fun invoke(id: String) = dataSource.deleteNote(id)
}