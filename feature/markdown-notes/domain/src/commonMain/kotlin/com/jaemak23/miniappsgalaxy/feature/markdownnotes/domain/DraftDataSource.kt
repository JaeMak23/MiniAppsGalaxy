package com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.DraftNote
import kotlinx.coroutines.flow.Flow

interface DraftDataSource {
    fun observeDraft(): Flow<DraftNote?>
    suspend fun getDraft(): Result<DraftNote?, DataError.Local>
    suspend fun saveDraft(draft: DraftNote): EmptyResult<DataError.Local>
    suspend fun clearDraft(): EmptyResult<DataError.Local>
}