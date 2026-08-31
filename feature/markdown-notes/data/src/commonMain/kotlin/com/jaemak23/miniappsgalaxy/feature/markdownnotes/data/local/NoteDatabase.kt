package com.jaemak23.miniappsgalaxy.feature.markdownnotes.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [NoteEntity::class, DraftEntity::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract val noteDao: NoteDao
    abstract val draftDao: DraftDao
}