package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data

import com.jaemak23.miniappsgalaxy.core.domain.DataError
import com.jaemak23.miniappsgalaxy.core.domain.EmptyResult
import com.jaemak23.miniappsgalaxy.core.domain.Result
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local.DraftDao
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.mapper.toDraftEntity
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.mapper.toDraftNote
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.DraftDataSource
import com.jaemak23.miniappsgalaxy.feature.markdownnotes.domain.model.DraftNote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomDraftDataSource(private val dao: DraftDao) : DraftDataSource {
    override fun observeDraft(): Flow<DraftNote?> {
        return dao.observeDraft().map { it?.toDraftNote() }
    }

    override suspend fun getDraft(): Result<DraftNote?, DataError.Local> {
        return try {
            Result.Success(dao.getDraft()?.toDraftNote())
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun saveDraft(draft: DraftNote): EmptyResult<DataError.Local> {
        return try {
            dao.upsertDraft(draft.toDraftEntity())
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }

    override suspend fun clearDraft(): EmptyResult<DataError.Local> {
        return try {
            dao.clearDraft()
            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(DataError.Local.UNKNOWN)
        }
    }
}