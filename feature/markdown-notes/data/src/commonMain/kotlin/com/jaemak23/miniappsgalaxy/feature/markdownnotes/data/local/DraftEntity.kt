package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "draft")
data class DraftEntity(
    @PrimaryKey val id: String = SINGLETON_ID,
    val filePath: String?,
    val title: String,
    val content: String,
    val updatedAt: Long
) {
    companion object {
        const val SINGLETON_ID = "draft"
    }
}