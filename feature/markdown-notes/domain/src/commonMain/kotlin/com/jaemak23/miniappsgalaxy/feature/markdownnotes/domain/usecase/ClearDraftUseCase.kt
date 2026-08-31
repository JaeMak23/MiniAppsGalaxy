package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.usecase

import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.DraftDataSource

class ClearDraftUseCase(private val dataSource: DraftDataSource) {
    suspend operator fun invoke() = dataSource.clearDraft()
}