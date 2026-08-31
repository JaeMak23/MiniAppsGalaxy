package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.DraftDataSource
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.DraftNote

class SaveDraftUseCase(private val dataSource: DraftDataSource) {
    suspend operator fun invoke(filePath: String?, title: String, content: String): EmptyResult<DataError.Local> {
        return dataSource.saveDraft(
            DraftNote(
                filePath = filePath,
                title = title,
                content = content,
                updatedAt = System.currentTimeMillis()
            )
        )
    }
}