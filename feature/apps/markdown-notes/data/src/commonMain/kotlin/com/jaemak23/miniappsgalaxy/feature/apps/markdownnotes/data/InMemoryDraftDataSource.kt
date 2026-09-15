package com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.data

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.DraftDataSource
import com.jaemak23.miniappsgalaxy.feature.apps.markdownnotes.domain.model.DraftNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InMemoryDraftDataSource : DraftDataSource {
    private val draft = MutableStateFlow<DraftNote?>(null)

    override fun observeDraft(): Flow<DraftNote?> = draft.asStateFlow()

    override suspend fun getDraft(): Result<DraftNote?, DataError.Local> =
        Result.Success(draft.value)

    override suspend fun saveDraft(draft: DraftNote): EmptyResult<DataError.Local> {
        this.draft.value = draft
        return Result.Success(Unit)
    }

    override suspend fun clearDraft(): EmptyResult<DataError.Local> {
        draft.value = null
        return Result.Success(Unit)
    }
}