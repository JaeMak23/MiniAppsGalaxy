package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface DraftDao {
    @Query("SELECT * FROM draft WHERE id = '${DraftEntity.SINGLETON_ID}' LIMIT 1")
    fun observeDraft(): Flow<DraftEntity?>

    @Query("SELECT * FROM draft WHERE id = '${DraftEntity.SINGLETON_ID}' LIMIT 1")
    suspend fun getDraft(): DraftEntity?

    @Upsert
    suspend fun upsertDraft(draft: DraftEntity)

    @Query("DELETE FROM draft")
    suspend fun clearDraft()
}